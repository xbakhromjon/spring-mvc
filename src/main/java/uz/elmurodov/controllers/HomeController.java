package uz.elmurodov.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import uz.elmurodov.annotations.MvcController;

@MvcController
@RequestMapping
public class HomeController {

    @RequestMapping(value = {"/", "/home"})
    public String homePage() {
        return "home";
    }

}
