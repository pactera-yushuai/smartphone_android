package com.pactera.adapter.recyclerviewadapter

import com.pactera.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pactera.bean.Goods

class GoodsSkeletonAdapter
/**
 * 构造方法，此示例中，在实例化Adapter时就传入了一个List。
 * 如果后期设置数据，不需要传入初始List，直接调用 super(layoutResId); 即可
 */
(list: MutableList<Goods>?) : BaseQuickAdapter<Goods, BaseViewHolder>(R.layout.cell_home, list), LoadMoreModule {
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


//        helper.setText(R.id.cell_temp_text_view1, String.valueOf(item.getGoodId()));
//        helper.setText(R.id.cell_temp_text_view2, item.getGoodName());
//        Glide.with(getContext())
//                .load(item.getGoodUrlSmall())
//                .placeholder(R.drawable.bg_graydialog)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//
//                        return false;
//                    }
//                })
//                .into((ImageView) helper.getView(R.id.cell_temp_image_view));
    }
}