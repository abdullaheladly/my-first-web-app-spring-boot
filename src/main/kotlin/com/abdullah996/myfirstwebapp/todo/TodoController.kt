package com.abdullah996.myfirstwebapp.todo

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@Controller
@SessionAttributes("name")
class TodoController constructor(private  val todoService: TodoService) {

    @RequestMapping("list-todo")
    fun listAllTodos(modelMap: ModelMap):String{
        modelMap.addAttribute("todos",todoService.getTodosList())
        return "listTodos"
    }

    @RequestMapping("delete-todo")
    fun listAllTodos(@RequestParam id:Int):String{
        todoService.deleteTodo(id)
        return "redirect:list-todo"
    }
    @RequestMapping("update-todo",method = [RequestMethod.GET])
    fun showUpdateTodoPage(@RequestParam id:Int,modelMap: ModelMap):String{
        val username=modelMap.getAttribute("name").toString()
        val todo=todoService.findById(id)
        modelMap.put("todo",todo)
        return "todo"
    }

    @RequestMapping("update-todo", method = [RequestMethod.POST])
    fun updateTodo(modelMap: ModelMap, @Valid @ModelAttribute("todo")  todo: Todo, result: BindingResult):String{
        if (result.hasErrors()){
            return "todo"
        }
        val username=modelMap.getAttribute("name").toString()

        val updatedTodo=todo.copy(userName =username )
        todoService.updateTodo(updatedTodo)
        return "redirect:list-todo"
    }

    @RequestMapping("add-todo", method = [RequestMethod.GET])
    fun showNewTodoPage(modelMap: ModelMap):String{
        val username=modelMap.getAttribute("name").toString()

        val todo=Todo(0,username,"", LocalDate.now().plusYears(1),false)
        modelMap.put("todo",todo)
        return "todo"
    }
    @RequestMapping("add-todo", method = [RequestMethod.POST])
    fun addNewTodo(modelMap: ModelMap, @Valid @ModelAttribute("todo")  todo: Todo, result: BindingResult):String{
        if (result.hasErrors()){
            return "todo"
        }
        val username=modelMap.getAttribute("name").toString()
        todoService.addTodo(username,todo.desc, todo.targetDate,false)
        return "redirect:list-todo"
    }
}