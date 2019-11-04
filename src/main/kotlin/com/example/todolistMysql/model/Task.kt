package com.example.todolistMysql.model

data class Task (val id: Long,
                 val content: String,
                 val done: Boolean)