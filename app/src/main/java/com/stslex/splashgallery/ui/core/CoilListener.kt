package com.stslex.splashgallery.ui.core

import coil.request.ImageRequest
import coil.request.ImageResult

@JvmInline
value class CoilListener(val function: () -> Unit) : ImageRequest.Listener {

    override fun onCancel(request: ImageRequest) {
        super.onCancel(request)
        function()
    }

    override fun onSuccess(request: ImageRequest, metadata: ImageResult.Metadata) {
        super.onSuccess(request, metadata)
        function()
    }
}