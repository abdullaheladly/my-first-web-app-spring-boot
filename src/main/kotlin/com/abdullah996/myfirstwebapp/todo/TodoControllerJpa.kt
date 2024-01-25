package com.abdullah996.myfirstwebapp.todo

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@Controller
@SessionAttributes("name")
class TodoControllerJpa constructor(private val todoRepository: TodoRepository) {

    @RequestMapping("list-todo")
    fun listAllTodos(modelMap: ModelMap):String{

        modelMap.addAttribute("todos",todoRepository.findByUserName(getLoginUserName()))
        return "listTodos"
    }


    fun getLoginUserName():String{
        return SecurityContextHolder.getContext().authentication.name
    }

    @RequestMapping("delete-todo")
    fun listAllTodos(@RequestParam id:Int):String{
        todoRepository.deleteById(id)
        return "redirect:list-todo"
    }
    @RequestMapping("update-todo",method = [RequestMethod.GET])
    fun showUpdateTodoPage(@RequestParam id:Int,modelMap: ModelMap):String{
        val username=getLoginUserName()
        val todo=todoRepository.findById(id).get()
        modelMap.put("todo",todo)
        return "todo"
    }

    @RequestMapping("update-todo", method = [RequestMethod.POST])
    fun updateTodo(modelMap: ModelMap, @Valid @ModelAttribute("todo")  todo: Todo, result: BindingResult):String{
        if (result.hasErrors()){
            return "todo"
        }
        val username=getLoginUserName()

        val updatedTodo=todo.copy(userName =username )
        todoRepository.save(updatedTodo)
        return "redirect:list-todo"
    }

    @RequestMapping("add-todo", method = [RequestMethod.GET])
    fun showNewTodoPage(modelMap: ModelMap):String{
        val username=getLoginUserName()

        val todo=Todo(userName = username, desc = "", targetDate =  LocalDate.now().plusYears(1), done = false)
        modelMap.put("todo",todo)
        return "todo"
    }
    @RequestMapping("add-todo", method = [RequestMethod.POST])
    fun addNewTodo(modelMap: ModelMap, @Valid @ModelAttribute("todo")  todo: Todo, result: BindingResult):String{
        if (result.hasErrors()){
            return "todo"
        }
        val username=getLoginUserName()
        todoRepository.save(todo.copy(userName = username))
        return "redirect:list-todo"
    }
}