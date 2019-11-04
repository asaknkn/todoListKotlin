package com.example.todolistMysql.mapper

import com.example.todolistMysql.model.Task
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Mapper
@Component
interface TaskRepository {
    fun create(content: String)
    fun update(task: Task)
    fun findALL(): List<Task>
    fun findById(id: Long): Task?
}