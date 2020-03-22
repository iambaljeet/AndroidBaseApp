package com.architecture.project.utility

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit

@GlideModule
class GlideAppModule: AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        //User to manage cache sizes for keeping images in cache
        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20mb

        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, memoryCacheSizeBytes.toLong()))
        builder.setBitmapPool(LruBitmapPool(memoryCacheSizeBytes.toLong()))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        //Used to provide timeout for any image loading request
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .connectTimeout(10, TimeUnit.MINUTES)
            .build()
        val factory = OkHttpUrlLoader.Factory(client)

        glide.registry.replace<GlideUrl, InputStream>(
            GlideUrl::class.java,
            InputStream::class.java, factory
        )
    }
}