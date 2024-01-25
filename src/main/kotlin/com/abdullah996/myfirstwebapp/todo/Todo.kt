package com.abdullah996.myfirstwebapp.todo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.Size
import java.time.LocalDate


@Entity
data class Todo(
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Int = 0,
     val userName:String="",
     @Size(min = 10, message = "enter at least 10 characters")
     @Column(name = "`desc`")
     val desc:String="",
     val targetDate: LocalDate= LocalDate.now(),
     val done:Boolean=false
)
