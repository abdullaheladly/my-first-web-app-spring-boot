package com.abdullah996.myfirstwebapp.hello

import org.apache.el.stream.Stream
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class SayHelloController {

    //"say hello" -> "Hello "

    @RequestMapping("/say-hello")
    @ResponseBody
    fun sayHello():String{
        return "Hello Abdullah"
    }

    @RequestMapping("/say-hello-html")
    @ResponseBody
    fun sayHelloHtml():String{
        val  stringBuffer=StringBuffer()
        stringBuffer.append("<html>")
        stringBuffer.append("<head>")
        stringBuffer.append("<title> my title </title>")
        stringBuffer.append("<head>")
        stringBuffer.append("<body>")
        stringBuffer.append("my body")
        stringBuffer.append("<body>")
        stringBuffer.append("<html>")
        return stringBuffer.toString()
    }


    @RequestMapping("/say-hello-jsp")
    fun sayHelloJsp():String{
        return "sayHello"
    }
}