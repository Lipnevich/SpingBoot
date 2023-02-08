package org.example.api;

import org.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotEmpty;

@Controller
@RequestMapping("email")
public class EmailApi {

    @Autowired
    private EmailService emails;

    @ResponseBody
    @PostMapping(value = "/add/{email}", consumes = "application/json", produces = "application/json")
    public String addEmail(@NotEmpty @PathVariable("email") String email) {
        emails.add(1L, email);
        return "Email added";
    }

    @ResponseBody
    @PutMapping(value = "/update/{oldEmail}/{newEmail}", consumes = "application/json", produces = "application/json")
    public String changeEmail(@NotEmpty @PathVariable("oldEmail") String oldEmail,
                              @NotEmpty @PathVariable("newEmail") String newEmail) {
        emails.change(1L, oldEmail, newEmail);
        return "Email changed";
    }

    @ResponseBody
    @DeleteMapping(value = "/delete/{email}", consumes = "application/json", produces = "application/json")
    public String removeEmail(@NotEmpty @PathVariable("email") String email) {
        emails.remove(email);
        return "Email removed";
    }



}