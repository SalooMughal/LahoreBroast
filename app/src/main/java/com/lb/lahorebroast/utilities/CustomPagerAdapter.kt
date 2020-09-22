package com.lb.lahorebroast.utilities
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import com.lb.lahorebroast.R
import com.lb.lahorebroast.model.Promotions
import kotlinx.android.synthetic.main.banner_layout.view.*

class CustomPagerAdapter(private val context: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private var models: List<Promotions.Datum>? = null
    override fun getCount(): Int {
        if(models!=null)
        {
            return models!!.size
        }
        return 0
    }

     fun setData(models: List<Promotions.Datum>)
    {
        this.models = models
        notifyDataSetChanged()
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
        return view == `object`
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater!!.inflate(R.layout.banner_layout, container, false)

        val bannerImageWidget : ImageWidget = view.bannerImageView



        models?.get(position)?.let { data ->
            bannerImageWidget.apply {

                loadImageFromURL(data.url!!)
            }

            container.addView(view, 0)
        }

        return view
    }

    override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
        container.removeView(`object` as View)
    }
}