package com.lb.lahorebroast.utilities

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Outline
import android.os.Build
import android.util.AttributeSet
import android.util.Base64
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

     fun setRoundImage(radius : Float)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outlineProvider = object : ViewOutlineProvider() {

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0, 0, view!!.width, view.height, radius)
                }
            }
            clipToOutline = true
        }
    }
    fun setBase64Image(base64String : String)
    {
        val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        setImageBitmap(decodedImage)
    }
    fun loadImageFromURL(url : String?)
    {
        if(null != url) {
            Picasso.with(context).load(url).fit().into(this)
        }
    }
     fun loadImageFromUrl(url : String?)
    {
        if(null != url) {
            Picasso.with(context).load(Utilities.imageBaseURL + url).fit().into(this)
        }
    }

}