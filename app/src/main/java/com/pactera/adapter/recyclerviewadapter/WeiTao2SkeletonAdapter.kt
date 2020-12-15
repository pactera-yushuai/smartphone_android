package com.pactera.adapter.recyclerviewadapter

import com.pactera.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pactera.bean.WeiTao2

/**
 * 构造方法，此示例中，在实例化Adapter时就传入了一个List。
 * 如果后期设置数据，不需要传入初始List，直接调用 super(layoutResId); 即可
 */
class WeiTao2SkeletonAdapter(list: MutableList<WeiTao2>?) : BaseQuickAdapter<WeiTao2, BaseViewHolder>(R.layout.cell_weitao2_skeleton, list), LoadMoreModule {
    /**
     * 在此方法中设置item数据
     */
    override fun convert(helper: BaseViewHolder, item: WeiTao2) {

    }
}