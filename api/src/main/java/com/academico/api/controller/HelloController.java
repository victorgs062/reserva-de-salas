package com.academico.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping
    @RequestMapping("/hello")
    public String helloControler(){
        return "hello";
    }
   // @PutMapping
   // @DeleteMapping

}
