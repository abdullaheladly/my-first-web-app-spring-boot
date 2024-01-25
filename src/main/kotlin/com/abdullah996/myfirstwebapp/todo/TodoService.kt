package com.abdullah996.myfirstwebapp.todo

import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class TodoService {

    companion object{
        private  val todos :MutableList<Todo> = mutableListOf()

        private var todosCount=0
        init {
            todos.add(Todo(++todosCount,"abdullah","learn spring", LocalDate.now().plusYears(1),false))
            todos.add(Todo(++todosCount,"ahmed","learn AWS", LocalDate.now().plusYears(1),false))
            todos.add(Todo(++todosCount,"abdullah","learn Jsp", LocalDate.now().plusYears(1),false))
            todos.add(Todo(++todosCount,"abdullah","learn hypernate", LocalDate.now().plusYears(1),false))
        }
    }
    fun getTodosList(name:String):List<Todo>{
        return todos.filter { it.userName.equals(name,ignoreCase = true) }
    }

    fun addTodo(userName:String,desc:String,localDate: LocalDate,isDone:Boolean){
        todos.add(Todo(++todosCount,userName,desc, localDate,isDone))
    }

    fun deleteTodo(id:Int?){
        todos.removeIf { it.id==id }
    }

    fun updateTodo(todo: Todo){
       deleteTodo(todo.id)
        todos.add(todo)
    }

    fun findById(id:Int):Todo{
        return todos.first { it.id==id }
    }


}