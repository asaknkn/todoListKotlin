package com.example.todolistMysql.service


import com.example.todolistMysql.model.Task
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat
import org.springframework.test.context.jdbc.Sql

@RunWith(value = SpringRunner::class)
@SpringBootTest
@Sql(statements = arrayOf("DELETE FROM task", "ALTER TABLE task auto_increment = 1"))
class TaskServiceTest {

    @Autowired
    private lateinit var sut: TaskService

    @Test
    fun 何も作成しなければfindAllは空のリストを返すこと() {
        val got = sut.findALL()
        assertThat(got).isEmpty()
    }

    @Test
    fun createで作成したタスクをfindByIdで取得できること() {
        val sucTask = Task(1, "success", false)
        val errorTask = Task(1, "error", false)

        sut.create(sucTask.content)
        val got: Task = sut.findById(sucTask.id) ?: errorTask
        assertThat(got.content).isEqualTo("success")
    }

    @Test
    fun updateされたtaskのdoneがtrueになっていること() {
        val task = Task(1, "sample", false)
        val errorTask = Task(1, "error", false)
        val newTask = task.copy(done = true)

        sut.create(task.content)
        sut.update(newTask)
        val got: Task = sut.findById(newTask.id) ?: errorTask
        println(got)
        assertThat(got.done).isEqualTo(true)
    }
}