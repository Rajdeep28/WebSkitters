package com.rajdeep.assesmentwebskitters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rajdeep.assesmentwebskitters.fragments.HomeFragment
import com.rajdeep.assesmentwebskitters.fragments.MapFragment
import com.rajdeep.assesmentwebskitters.fragments.UserFragment
import kotlinx.android.synthetic.main.activity_another.*

class AnotherActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val mapFragment = MapFragment()
    private val userFragment = UserFragment()

    //var personDataBase: PersonDataBase?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
        replaceFragment(homeFragment)


        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.map -> replaceFragment(mapFragment)
                R.id.user -> replaceFragment(userFragment)
            }
            true
        }
        //personDataBase = Room.databaseBuilder(applicationContext,PersonDataBase::class.java,"person_data_databse").allowMainThreadQueries().build()
    }
    private fun replaceFragment(fragment: Fragment){
        if (fragment != null){
            val transition = supportFragmentManager.beginTransaction()
            transition.replace(R.id.fragment_container,fragment)
            transition.commit()
        }
    }
}