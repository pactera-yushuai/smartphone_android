package com.pactera.adapter.viewpageradapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pactera.R
import com.bumptech.glide.Glide
import com.pactera.bean.Banner
import com.youth.banner.adapter.BannerAdapter

/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
class ImageAdapter(mDatas: List<Banner?>?) : BannerAdapter<Banner?, ImageAdapter.ImageViewHolder>(mDatas) {
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ImageViewHolder(imageView)
    }

    override fun onBindView(holder: ImageViewHolder?, data: Banner?, position: Int, size: Int) {
        Glide.with(holder?.itemView!!)
                .load(data?.urlBig)
//                .load(R.mipmap.guanggao1)
//                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);
//        holder?.imageView?.setImageResource(R.mipmap.shanping)
    }

    public inner class ImageViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)

}