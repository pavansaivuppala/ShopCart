package com.example.shopcart.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shopcart.model.Constant.SUB_CATEGORY_ID
import com.example.shopcart.model.subcategory.Subcategory
import com.example.shopcart.view.AndroidFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val subCategoryList: ArrayList<Subcategory>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun createFragment(position: Int): Fragment {

        return AndroidFragment().apply {
            val bundle = Bundle(1)
            bundle.putString(SUB_CATEGORY_ID, subCategoryList[position].subcategory_id) // key , value
            this.arguments = bundle
        }
    }

}