package com.pactera.adapter.recyclerviewadapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.pactera.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pactera.bean.Goods

/**
 * 构造方法，此示例中，在实例化Adapter时就传入了一个List。
 * 如果后期设置数据，不需要传入初始List，直接调用 super(layoutResId); 即可
 */
class GoodsAdapter(list: MutableList<Goods>?) : BaseQuickAdapter<Goods, BaseViewHolder>(R.layout.cell_home, list), LoadMoreModule {
    /**
     * 在此方法中设置item数据
     */
    override fun convert(helper: BaseViewHolder, item: Goods) {

//        helper.itemView.shimmerLayoutCell.apply {
//            setShimmerColor(0x55FFFFFF)
//            setShimmerAngle(0)
//            startShimmerAnimation()
//        }

//        ShimmerFrameLayout shimmer = (ShimmerFrameLayout)helper.itemView.findViewById(R.id.shimmer_view_container);
//        shimmer.stopShimmer();
        helper.setText(R.id.cell_home_text_view1, item.goodName)
        helper.setText(R.id.cell_home_text_view2, R.string.remai)
        helper.setText(R.id.cell_home_text_view3, "￥"+item.goodPrice)
        Glide.with(context)
                .load(item.goodUrlSmall)
                .placeholder(R.drawable.bg_graydialog)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into((helper.getView<View>(R.id.cell_home_image_view) as ImageView))
    }
}