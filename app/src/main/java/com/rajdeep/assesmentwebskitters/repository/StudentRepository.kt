package com.rajdeep.assesmentwebskitters.repository

import com.rajdeep.assesmentwebskitters.db.Student
import com.rajdeep.assesmentwebskitters.db.StudentDao

class StudentRepository(private val studentDao: StudentDao) {

    val students = studentDao.getAllStudents()

    suspend fun insert(student: Student){
        studentDao.insertStudentDetails(student)
    }
    suspend fun update(student: Student) = studentDao.updateStudentDetails(student)

    suspend fun delete(student: Student) = studentDao.deleteStudentDetails(student)

    suspend fun deleteAll() = studentDao.deleteAll()

}