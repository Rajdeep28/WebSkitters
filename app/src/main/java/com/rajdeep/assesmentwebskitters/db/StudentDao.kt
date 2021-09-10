package com.rajdeep.assesmentwebskitters.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudentDetails(student: Student)

    @Update
    suspend fun updateStudentDetails(student: Student)

    @Delete
    suspend fun deleteStudentDetails(student: Student)

    @Query("DELETE FROM student_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM student_data_table")
    fun getAllStudents(): LiveData<List<Student>>

}