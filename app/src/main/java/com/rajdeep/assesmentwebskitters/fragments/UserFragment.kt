package com.rajdeep.assesmentwebskitters.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rajdeep.assesmentwebskitters.R
import com.rajdeep.assesmentwebskitters.StudentAddDetailsActivity
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment: Fragment() {

    lateinit var fab_btn: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_user,container,false)
        fab_btn = rootView.findViewById(R.id.fab_btn)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_btn.setOnClickListener {
            //FirebaseAuth.getInstance().signOut()
            val intent = Intent (activity, StudentAddDetailsActivity::class.java)
            startActivity(intent)
        }
    }

}