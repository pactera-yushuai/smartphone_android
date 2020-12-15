package com.pactera.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.pactera.R
import kotlinx.android.synthetic.main.ys_search_layout.view.*


class YSSearchView(context : Context, attrs: AttributeSet) : LinearLayout(context,attrs) {

    init {

        LayoutInflater.from(context).inflate(R.layout.ys_search_layout, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSSearchView)

//        ys_linear_layout_vertical_text_view.text = attributes.getString(R.styleable.YSLinearLayoutVerticalImage_top_text_view_text)

        attributes.recycle()

        /**
         * 光标置后
         */
//        val etext = edit_text.text
//        Selection.setSelection(etext, etext.length)
//        iv_cleartext.visibility = View.VISIBLE

        ys_search_layout_iv_cleartext.setOnClickListener{
            ys_search_layout_edit_text.setText("")
        }


        ys_search_layout_edit_text.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    ys_search_layout_iv_cleartext.visibility = View.VISIBLE
                } else {
                    ys_search_layout_iv_cleartext.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable) {

            }

        })
        

    }


    fun getEditText(): EditText{
        return ys_search_layout_edit_text
    }


    fun getSearchButton(): Button{
        return ys_search_layout_tv_search
    }
    

}
