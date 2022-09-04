package com.stslex.core_ui

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.request.RequestListener
import com.google.android.material.transition.platform.MaterialContainerTransform
import dagger.Lazy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    private val hostFragmentId: Int
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = hostFragmentId
            duration = resources.getInteger(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    private val requestListener: RequestListener<Drawable>
        get() = GlideRequestListener(::postponeEnterTransition)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun <T> Flow<T>.launchWhenStarted(
        collector: (T) -> Unit
    ) {
        viewScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                collect(collector)
            }
        }
    }

    val viewScope: CoroutineScope
        get() = viewLifecycleOwner.lifecycleScope
}