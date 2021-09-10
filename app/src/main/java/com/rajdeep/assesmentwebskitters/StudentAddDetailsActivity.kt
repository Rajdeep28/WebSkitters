package com.rajdeep.assesmentwebskitters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajdeep.assesmentwebskitters.adapter.StudentRecyclerViewAdapter
import com.rajdeep.assesmentwebskitters.databinding.ActivityStudentAddDetailsBinding
import com.rajdeep.assesmentwebskitters.db.Student
import com.rajdeep.assesmentwebskitters.db.StudentDao
import com.rajdeep.assesmentwebskitters.repository.StudentRepository
import com.rajdeep.assesmentwebskitters.viewmodel.StudentViewModel
import com.rajdeep.assesmentwebskitters.viewmodel.StudentViewModelFoctory

class StudentAddDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityStudentAddDetailsBinding
    lateinit var studenViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_student_add_details)

        var dao: StudentDao = StudentDatabase.getInstance(application).studentDao
        var repository = StudentRepository(dao)
        var factory = StudentViewModelFoctory(repository)
        studenViewModel = ViewModelProvider(this,factory).get(StudentViewModel::class.java)
        binding.myViewModel = studenViewModel
        binding.lifecycleOwner = this

        displayStudentList()

        showDetailsRecyclerView()
    }

    private fun showDetailsRecyclerView() {
        binding.studentRecyclerView.layoutManager = LinearLayoutManager(this)
        displayStudentList()
    }

    private fun displayStudentList(){
        studenViewModel.students.observe(this, Observer {
            Log.i("MyTAG", it.toString())
            binding.studentRecyclerView.adapter = StudentRecyclerViewAdapter(it,{selectedItem: Student ->listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(student: Student){
        Toast.makeText(this,"Selected name is:${student.name}",Toast.LENGTH_SHORT).show()
        studenViewModel.initUpdateAndDelete(student)
    }
}