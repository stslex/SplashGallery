package com.stslex.splashgallery.ui.core

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.stslex.core_ui.ImageSetter
import com.stslex.core_ui.SetImageWithGlide
import com.stslex.splashgallery.R
import dagger.Lazy
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
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    private val requestListener: RequestListener<Drawable>
        get() = object : RequestListener<Drawable> {

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                postponeEnterTransition()
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                postponeEnterTransition()
                return false
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}