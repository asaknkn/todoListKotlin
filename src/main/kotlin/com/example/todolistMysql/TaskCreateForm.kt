package com.example.todolistMysql

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class TaskCreateForm {
    @NotBlank
    @Size(max = 20)
    var content: String? = null
}