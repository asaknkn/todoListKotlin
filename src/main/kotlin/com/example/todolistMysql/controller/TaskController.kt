package com.example.todolistMysql.controller

import com.example.todolistMysql.NotFoundException
import com.example.todolistMysql.TaskCreateForm
import com.example.todolistMysql.TaskUpdateForm
import com.example.todolistMysql.model.Task
import com.example.todolistMysql.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("tasks")
class TaskController() {

    @Autowired
    lateinit var service: TaskService

    @GetMapping("")
    fun index(model: Model): String {
        val tasks = service.findALL()
        model.addAttribute("tasks", tasks)
        return "tasks/index"
    }

    @GetMapping("new")
    fun new(form: TaskCreateForm): String {
        return "tasks/new"
    }

    @PostMapping("")
    fun create(@Validated form: TaskCreateForm,
               bindingResult: BindingResult): String {
        if (bindingResult.hasErrors())
            return "tasks/new"

        val content = requireNotNull(form.content)
        service.create(content)
        return "redirect:/tasks"
    }

    @GetMapping("{id}/edit")
    fun edit(@PathVariable("id") id: Long,
             form: TaskUpdateForm): String {
        val task: Task = service.findById(id) ?: throw NotFoundException()
        form.content = task.content
        form.done = task.done
        return "tasks/edit"
    }

    @PostMapping("{id}")
    fun update(@PathVariable("id") id: Long,
               @Validated form: TaskUpdateForm,
               bidingResult: BindingResult): String {
        if (bidingResult.hasErrors())
            return "tasks/edit"

        val task: Task = service.findById(id) ?: throw NotFoundException()
        val newTask = task.copy(content = requireNotNull(form.content),
                done = form.done)
        service.update(newTask)
        return "redirect:/tasks"
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(): String = "tasks/not_found"
}