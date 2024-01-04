package com.example.wsselixir.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.lifecycle.Lifecycle
import com.example.wsselixir.view.fragment.FollowerFragment
import com.example.wsselixir.view.fragment.UserFragment

private const val NUM_TABS = 2

class DetailAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return UserFragment()
            1 -> return FollowerFragment()

        }
        return FollowerFragment()
    }
}