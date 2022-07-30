package com.mahmoudsalah.shoptask.home

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
import com.mahmoudsalah.shoptask.databinding.ItemCustomViewBinding
import com.mahmoudsalah.shoptask.model.Result

class HomeRecyclerViewAdapter(
    private var data: List<Result>,
    var context: Context,
    var itemClick: ItemClickInterface,
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.VH>() {
    class VH(val myView: ItemCustomViewBinding) : RecyclerView.ViewHolder(myView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val viewBinding =
            ItemCustomViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(viewBinding)
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
        val name = data[position].name.split(",")
        val price = data[position].price
        Glide.with(context).load(data[position].image_urls_thumbnails[0])
            .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .listener(object :
                RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                holder.myView.progressBar.visibility = View.VISIBLE
                return false
            }
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                holder.myView.progressBar.visibility = View.GONE
                return false
            }

        }).into(holder.myView.itemImage)

        holder.myView.itemName.text = name[0]
        holder.myView.priceTxt.text = price
        holder.itemView.setOnClickListener {
            itemClick.onClickItem(data[position])
        }

    }

    override fun getItemCount() = data.size


}

