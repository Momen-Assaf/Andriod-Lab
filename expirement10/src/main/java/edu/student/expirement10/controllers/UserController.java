package edu.student.expirement10.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.student.expirement10.models.User;
import edu.student.expirement10.services.UserService;
@RestController
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping("/users")
    public List<User> getAllUsers(){
        return service.getAll();
    }
    @PostMapping("/users")
    public String addOne(@RequestBody User user) {
        return service.addUser(user);
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        return service.deleteUser(id);
    }

}