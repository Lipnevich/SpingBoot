package org.example.api;

import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("auth")
public class LoginApi {

    @Autowired
    private UserService service;

    @PostMapping(value = "/create/{user}/{pass}", consumes = "application/json", produces = "application/json")
    public void login(HttpServletRequest req, String user, String pass) {
//        UsernamePasswordAuthenticationToken authReq
//                = new UsernamePasswordAuthenticationToken(user, pass);
//        Authentication auth = authManager.authenticate(authReq);
//
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(auth);
//        HttpSession session = req.getSession(true);
//        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
    }

}