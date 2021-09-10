package com.rajdeep.assesmentwebskitters.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajdeep.assesmentwebskitters.db.Student
import com.rajdeep.assesmentwebskitters.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(private var studentRepository: StudentRepository): ViewModel(), Observable {

    val students = studentRepository.students
    private var isUpdateOrDelete = false
    private lateinit var studentToUpdateOrDelete: Student

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputMail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButton = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButton = MutableLiveData<String>()

    init {
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    fun saveOrUpdate(){

        if (isUpdateOrDelete){

            studentToUpdateOrDelete.name = inputName.value!!
            studentToUpdateOrDelete.email = inputMail.value!!
            update(studentToUpdateOrDelete)
        }else{
            var name:String = inputName.value!!
            var mail: String = inputMail.value!!
            insert(Student(0,name,mail))
            inputName.value = null
            inputMail.value = null
        }

    }

    fun clearAllOrDeleteAll(){

        if (isUpdateOrDelete){
            delete(studentToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun initUpdateAndDelete(student: Student){
        inputName.value = student.name
        inputMail.value = student.email
        isUpdateOrDelete = true
        studentToUpdateOrDelete = student

        saveOrUpdateButton.value = "Update"
        clearAllOrDeleteButton.value = "Delete"
    }

    fun insert(student: Student) = viewModelScope.launch {
        studentRepository.insert(student)
    }

    fun update(student: Student) = viewModelScope.launch {
        studentRepository.update(student)

        inputName.value = null
        inputMail.value = null
        isUpdateOrDelete = false
        studentToUpdateOrDelete = student

        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }
    fun delete(student: Student) = viewModelScope.launch {
        studentRepository.delete(student)
        inputName.value = null
        inputMail.value = null
        isUpdateOrDelete = false
        studentToUpdateOrDelete = student

        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    fun clearAll() = viewModelScope.launch {
        studentRepository.deleteAll()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }
}