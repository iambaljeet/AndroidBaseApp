package com.app.baseapp.utility

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

object ImageUtility {
    /**
     * Extension method to load imageView from url.
     */
    fun ImageView.loadFromUrl(imageUrl: String) {
        Glide.with(this).load(imageUrl).into(this)
    }

    /**
     * Extension method to load imageView from url as centercropped.
     */
    fun ImageView.loadCenterCroppedImageFromUrl(imageUrl: String) {
        Glide.with(this).load(imageUrl).centerCrop().into(this)
    }

    /**
     * Extension method to load imageView from url as centercropped with placeholder.
     */
    fun ImageView.loadCenterCroppedImageFromUrl(imageUrl: String, placeHolder: Int) {
        Glide.with(this).load(imageUrl).placeholder(placeHolder).centerCrop().into(this)
    }

    /**
     * Extension method to load imageView from url with placeholder.
     */
    fun ImageView.loadFromUrl(imageUrl: String, placeHolder: Int) {
        Glide.with(this).load(imageUrl).placeholder(placeHolder).centerCrop().into(this)
    }

    /**
     * Extension method to load resource into imageView.
     */
    fun ImageView.load(@DrawableRes imageResource: Int) {
        this.setImageResource(imageResource)
    }

    /**
     * Extension method to load drawable into imageView.
     */
    fun ImageView.load(imageDrawable: Drawable) {
        this.setImageDrawable(imageDrawable)
    }

    /**
     * Extension method to load bitmap int imageView.
     */
    fun ImageView.load(imageBitmap: Bitmap) {
        this.setImageBitmap(imageBitmap)
    }
}