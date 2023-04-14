package com.justice.justiceforall.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//o path raiz é o localhost:8080

@RestController
public class HelloSpring {

    @GetMapping(value="/hello")
    public String getMethodName() {
        return "Oiiiiiiiiiiiie Spring";
    }
    
}
