package com.example.ui.perdo

import android.content.Context
import coil.ImageLoader
import coil.decode.GifDecoder

internal fun createImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()
}