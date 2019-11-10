package com.example.todolistMysql.service

import com.example.todolistMysql.repository.TaskRepository
import com.example.todolistMysql.model.Task
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TaskService(private val taskRepository: TaskRepository) {
    fun create(content: String) = taskRepository.create(content)
    fun update(task: Task) = taskRepository.update(task)
    fun findALL(): List<Task> = taskRepository.findALL()
    fun findById(id: Long): Task? = taskRepository.findById(id)
}