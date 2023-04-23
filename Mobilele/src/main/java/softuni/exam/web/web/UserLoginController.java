package softuni.exam.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.exam.web.domain.dtos.model.UserLoginDto;

@Controller
public class UserLoginController{
    @GetMapping("/users/login")
    public String login(){
        return "auth-login";
        }

        @PostMapping("/users/login")
    public String login(UserLoginDto userLoginDto){
return "redirect:/";
        }
}
