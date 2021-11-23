package com.miatorresch.miguetienda.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.miatorresch.miguetienda.R
import com.miatorresch.miguetienda.databinding.ActivityHomeBinding
import com.miatorresch.miguetienda.view.ui.fragments.AdminFragment
import com.miatorresch.miguetienda.view.ui.fragments.CommentsFragment
import com.miatorresch.miguetienda.view.ui.fragments.HomeFragment
import com.miatorresch.miguetienda.view.ui.fragments.OrderFragment


class HomeActivity : AppCompatActivity() {


    private val homeFragment = HomeFragment()
    private val orderFragment = OrderFragment()
    private val commentsFragment = CommentsFragment()
    private val adminFragment = AdminFragment()

    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this,
            R.layout.activity_home)


        drawerLayout = binding.drawerLayout

        //val navController = this.findNavController(R.id.fragment_container)

        //NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)


        drawerLayout = binding.drawerLayout

        replaceFragment(homeFragment)

        var bottonNavigation = findViewById<BottomNavigationView>(R.id.bnvMenu)
        bottonNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHomeFragment -> replaceFragment(homeFragment)
                R.id.navOrderFragment -> replaceFragment(orderFragment)
                R.id.navCommentsFragment -> replaceFragment(commentsFragment)
                R.id.navAdminFragment -> replaceFragment(adminFragment)

            }
            true
        }

        //NavigationUI.setupWithNavController(binding.navView, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


    private fun configNav(){
       /* val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
        val navController = navHostFragment.navController
        var bnvMenu = findViewById<BottomNavigationView>(R.id.bnvMenu)
        bnvMenu.setupWithNavController(navController)

        */
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()

        }
    }


}