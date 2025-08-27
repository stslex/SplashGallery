package com.stslex.splashgallery.core_ui

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.request.RequestListener
import com.google.android.material.transition.MaterialContainerTransform
import dagger.Lazy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @Inject
    lateinit var imageSetter: Lazy<ImageSetter>

    @Inject
    lateinit var setImageWithGlideFactory: SetImageWithGlide.Factory

    val setImage: SetImageWithGlide by lazy {
        setImageWithGlideFactory.create { url, imageView, needCrop, needCircleCrop ->
            imageSetter.get().setImage(url, imageView, needCrop, needCircleCrop, requestListener)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            transitionDirection = resources.getInteger(R.integer.transition_duration)
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    private val requestListener: RequestListener<Drawable>
        get() = GlideRequestListener(::postponeEnterTransition)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun <T> Flow<T>.launchWhenStarted(
        collector: suspend (T) -> Unit
    ) {
        flowWithLifecycle(
            lifecycle = viewLifecycleOwner.lifecycle,
            minActiveState = Lifecycle.State.STARTED
        ).onEach { value ->
            collector(value)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}