package com.pactera.adapter.recyclerviewadapter

import android.view.View
import android.widget.ImageView
import com.pactera.R
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pactera.bean.WeiTao2
import com.pactera.widget.CircleImageView
import java.lang.String

/**
 * 构造方法，此示例中，在实例化Adapter时就传入了一个List。
 * 如果后期设置数据，不需要传入初始List，直接调用 super(layoutResId); 即可
 */
class WeiTao2Adapter(list: MutableList<WeiTao2>?) : BaseQuickAdapter<WeiTao2, BaseViewHolder>(R.layout.cell_weitao2, list), LoadMoreModule {
    /**
     * 在此方法中设置item数据
     */
    override fun convert(helper: BaseViewHolder, item: WeiTao2) {
        helper.setText(R.id.cell_weitao2_text_view1, String.valueOf(item.goodName))
//        helper.setText(R.id.cell_weitao2_text_view2, item.goodName)

        Glide.with(context)
                .load(item.goodUrlSmall)
                .placeholder(R.drawable.bg_graydialog)
                .into((helper.getView<View>(R.id.cell_weitao2_image_view_1) as CircleImageView))

        Glide.with(context)
                .load(item.goodUrlSmall)
                .placeholder(R.drawable.bg_graydialog)
                .into((helper.getView<View>(R.id.cell_weitao2_image_view_2) as CircleImageView))

        Glide.with(context)
                .load(item.goodUrlBig)
                .placeholder(R.drawable.bg_graydialog)
                .into((helper.getView<View>(R.id.cell_weitao2_image_view) as ImageView))
    }
}