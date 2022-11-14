package com.example.myfilms.screens.root

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.example.myfilms.MAIN
import com.example.myfilms.R
import com.example.myfilms.ViewPagerAdapter
import com.firebase.ui.auth.AuthUI
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_root.view.*


class RootFragment : Fragment(), MenuProvider {

    private var ctx: Context? = null
    private val auth = AuthUI.getInstance()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_root, container, false)
        view.tab_layout.tabIconTint = null
        view.view_pager.adapter = ViewPagerAdapter(ctx as FragmentActivity)
        TabLayoutMediator(view.tab_layout, view.view_pager){
                tab, pos ->
            when(pos){
                0 ->{
                    tab.setIcon(R.drawable.ic_baseline_local_movies_24)
                }
                1 ->{
                    tab.setIcon(R.drawable.ic_baseline_favorite)
                }
            }
        }.attach()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.item_favorite){
            MAIN.navController.navigate(R.id.action_rootFragment_to_infoFragment)
            return true
        }
        if (menuItem.itemId == R.id.item_logout) {
            auth.signOut(ctx as FragmentActivity)
            MAIN.navController.navigate(R.id.action_rootFragment_to_signInFragment)
            return true
        }
        return onMenuItemSelected(menuItem)
    }

}