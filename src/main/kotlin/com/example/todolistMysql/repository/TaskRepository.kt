package com.example.todolistMysql.repository

import com.example.todolistMysql.model.Task
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface TaskRepository {
    fun create(content: String)
    fun update(task: Task)
    fun findALL(): List<Task>
    fun findById(id: Long): Task?
}