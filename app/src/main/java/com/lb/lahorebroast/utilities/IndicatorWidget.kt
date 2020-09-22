package com.lb.lahorebroast.utilities

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.lb.lahorebroast.R


class IndicatorWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var dots : ArrayList<ImageView>

    fun addDots( viewPager: ViewPager, count : Int) {
        dots = ArrayList()
        for (i in 0 until count ) {
            val dot = ImageView(context)

            dot.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.unslected_indicator))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.marginStart = 8
            params.marginEnd = 8
            addView(dot, params)
            dots.add(dot)
        }
        selectDot(0,count)
        viewPager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                selectDot(position,count)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun selectDot(idx: Int , count: Int) {
        for (i in 0 until count) {
            val drawableId: Int =
                if (i == idx) R.drawable.selected_indicator else R.drawable.unslected_indicator
            val drawable: Drawable =ContextCompat.getDrawable(context,drawableId)!!
            dots.get(i).setImageDrawable(drawable)
        }
    }

}