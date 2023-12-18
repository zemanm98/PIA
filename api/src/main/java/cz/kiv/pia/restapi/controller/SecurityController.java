package cz.kiv.pia.restapi.controller;

import cz.kiv.pia.restapi.communication.RegisterForm;
import cz.kiv.pia.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.regex.Pattern;

@Controller
public class SecurityController {
    /**
     * Regex used for validating given email adress.
     */
    private String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private final UserService userService;
    /**
     * Encoder for encoding given passwords
     */
    private final BCryptPasswordEncoder encoder;

    public SecurityController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    /**
     * returns login template.
     * @return - login form
     */
    @GetMapping("/loginNormal")
    public String login(){
        return "login";
    }

    /**
     * Method returns registering template with RegisterForm object for transfering register data.
     * @param model - thymeleaf model for generating html response
     * @return - registering template
     */
    @GetMapping("/register")
    public String register(Model model){
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm", registerForm);
        return "register";
    }

    /**
     * Method saves new user or sends back some type of registering error.
     * @param model - thymeleaf model for generating html response
     * @param registerForm - Object used for data transfer of registering data of user.
     * @return - either returns error from registering or redirects user to login page.
     */
    @PostMapping("/register/save")
    public String registerSave(Model model, @ModelAttribute("registerForm") RegisterForm registerForm){
        if(!Pattern.compile(regexPattern).matcher(registerForm.getEmail()).matches()){
            model.addAttribute("emailError", "input valid email please");
            return "register";
        }
        if(userService.loadUserByUsername(registerForm.getEmail()) != null){
            model.addAttribute("userExists", "Something went wrong, try again");
            return "register";
        }
        if(userService.save(registerForm.getName(), registerForm.getEmail(), encoder.encode(registerForm.getPassword())) != 1){
            model.addAttribute("userError", "Something went wrong, try again");
            return "register";
        }
        return "redirect:/loginNormal";
    }
}
