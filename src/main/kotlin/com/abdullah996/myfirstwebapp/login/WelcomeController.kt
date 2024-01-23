package com.abdullah996.myfirstwebapp.login

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
@SessionAttributes("name")
class WelcomeController constructor() {

    @RequestMapping("/", method = [RequestMethod.GET])
    fun goToWelcomePage(modelMap: ModelMap):String{
        modelMap.put("name",getLoginUserName())
        return "welcome"
    }

    fun getLoginUserName():String{
        return SecurityContextHolder.getContext().authentication.name
    }
}