package uz.elmurodov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.elmurodov.dto.UserCreateDto;
import uz.elmurodov.response.ResponseEntity;
import uz.elmurodov.service.UserService;

@Controller
public class UserController extends AbstractController<UserService> {
    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping("/signup/")
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = "/signup/", method = RequestMethod.POST)
    public String signup(@ModelAttribute UserCreateDto userCreateDto, Model model) {
        ResponseEntity<Long> response = service.create(userCreateDto);
        if (response.getStatus().value() != 200) {
            model.addAttribute("status", response.getStatus());
            return "redirect:/error/exception";
        }
        return "redirect:/home";
    }

}
