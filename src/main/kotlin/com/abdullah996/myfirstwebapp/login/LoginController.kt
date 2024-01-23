package com.abdullah996.myfirstwebapp.login

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
@SessionAttributes("name")
class LoginController constructor(private val authenticationService: AuthenticationService) {

    @RequestMapping("/login", method = [RequestMethod.GET])
    fun login():String{
        return "login"
    }

    @RequestMapping("/login", method = [RequestMethod.POST])
    fun welcome(@RequestParam name:String,@RequestParam password:String,modelMap: ModelMap):String{
        modelMap.put("name",name)
        return if (authenticationService.authenticate(name, password)){
            "welcome"
        }else{
            modelMap.put("errorMessage","invalid please try again")
            "login"

        }
    }
}