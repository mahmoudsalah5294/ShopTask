package com.mahmoudsalah.shoptask.item_details

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mahmoudsalah.shoptask.databinding.CustomItemDetailsImageBinding

class ViewPagerAdapter(
    private val imagesURL: List<String>,
    private val context: Context
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    class ViewPagerViewHolder(val myView: CustomItemDetailsImageBinding) :
        RecyclerView.ViewHolder(myView.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val viewBinding =
            CustomItemDetailsImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        Glide.with(context).load(imagesURL[position]).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                holder.myView.progressBar.visibility = View.VISIBLE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                holder.myView.progressBar.visibility = View.GONE
                return false
            }

        }).into(holder.myView.offerImageView)
        holder.myView.offersNumber.text = "${position+1}/${imagesURL.size}"

    }

    override fun getItemCount() = imagesURL.size
}

