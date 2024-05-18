package edu.student.expirement9.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.student.expirement9.models.User;
import edu.student.expirement9.services.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/hello")
    public String sayHello() {
        return userService.sayHello();
    }

    @RequestMapping("/users")
    public ArrayList<User> getAllUsers() {
        return userService.getUserList();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public boolean addUser(@RequestBody User user) {
        return userService.addUserToList(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public boolean deleteUser(@PathVariable int id) {
        return userService.deleteUserFromList(id);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "users/{id}")
//    public boolean updateUser(@RequestBody User user, @PathVariable int id) {
//        return userService.updateUserInList(user,id);
//    }

    @RequestMapping(method = RequestMethod.PUT, value = "users/{id}")
    public boolean updateUserName(@RequestBody String name, @PathVariable int id) {
        return userService.updateUserNameInList(name, id);
    }

}