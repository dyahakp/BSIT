package com.dyah.bsit.presentation.adapter

import android.icu.text.CaseMap.Title
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter (fm :FragmentManager) : FragmentStatePagerAdapter(fm){

    private val listFragment : MutableList<Fragment> = mutableListOf()
    private val listTitle: MutableList<String> = mutableListOf()

    fun addFragment(fr : Fragment, title: String){
        listFragment.add(fr)
        listTitle.add(title)
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle[position]
    }

    override fun getCount(): Int {
        return listFragment.size

    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]

    }

}
