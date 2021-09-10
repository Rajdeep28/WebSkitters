package com.rajdeep.assesmentwebskitters.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajdeep.assesmentwebskitters.PhotosModel
import com.rajdeep.assesmentwebskitters.PictureAdapter
import com.rajdeep.assesmentwebskitters.R
import com.rajdeep.assesmentwebskitters.api.ApiService
import com.rajdeep.assesmentwebskitters.api.ServiceGenerator
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class HomeFragment: Fragment(){

    lateinit var adapter: PictureAdapter
    var photoModelsList: ArrayList<PhotosModel> =
        ArrayList<PhotosModel>()
    private var mRecyclerView: RecyclerView? = null

    lateinit var searchView: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_home,container,false)

        initView(view)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPhotos()

        call.enqueue(object : Callback<MutableList<PhotosModel>>{
            override fun onResponse(
                call: Call<MutableList<PhotosModel>>,
                response: Response<MutableList<PhotosModel>>
            ) {
                if (response.isSuccessful){
                    Log.d("Okhttp", "${response.body().toString()}")
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = PictureAdapter(response.body()!!)

                    }
                }
            }

            override fun onFailure(call: Call<MutableList<PhotosModel>>, t: Throwable) {
                t.printStackTrace()
                Log.d("Okhttp", t.message.toString())
            }

        })

        return rootView


    }

    private fun initView(view: View?) {
        if (view != null) {
            searchView = view.findViewById(R.id.searchView)
            mRecyclerView = view.findViewById(R.id.recyclerView)
            searchAssignedDetails()
        }
    }

    private fun searchAssignedDetails() {
        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                assignedFilter(s.toString())
            }
        })
    }

    private fun assignedFilter(text: String) {
        val achiveMentFilteredList: ArrayList<PhotosModel> =
            ArrayList()
        for (value in photoModelsList) {
            if (value.albumId!!.toLowerCase().contains(text.toLowerCase()))
            {
                achiveMentFilteredList.add(value)
            }
        }


        if (achiveMentFilteredList.size == 0) {
            tv_no_data_available.visibility = View.VISIBLE
            mRecyclerView!!.visibility=View.GONE
//            Toast.makeText(getContext(), "No data found !", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(achiveMentFilteredList)
            tv_no_data_available.visibility = View.GONE
            mRecyclerView?.visibility=View.VISIBLE
        }
    }


}