package com.abdullah996.myfirstwebapp.todo

import jakarta.validation.constraints.Size
import java.time.LocalDate

data class Todo(
     val id:Int,
     val userName:String,
     @Size(min = 10, message = "enter at least 10 characters")
     val desc:String,
     val targetDate: LocalDate,
     val done:Boolean
)
