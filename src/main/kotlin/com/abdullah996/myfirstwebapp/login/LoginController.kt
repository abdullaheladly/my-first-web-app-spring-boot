package com.abdullah996.myfirstwebapp.login

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class LoginController {

    private val logger=LoggerFactory.getLogger(javaClass)

    @RequestMapping("/login")
    fun login(@RequestParam name:String,model:ModelMap):String{

        model["x"] = name
        logger.debug(name)
        return "login"
    }
}