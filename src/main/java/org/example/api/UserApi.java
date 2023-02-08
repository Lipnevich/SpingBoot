package org.example.api;

import org.example.service.UserService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class UserApi {

    @Autowired
    private UserService service;

    @ResponseBody
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public User createUser(@Valid @RequestBody User user) {
        return service.create(user);
    }

    @ResponseBody
    @PostMapping(value = "/search", consumes = "application/json", produces = "application/json")
    public UserPage search(@RequestBody UserSearch search) {
        return service.search(search);
    }



}