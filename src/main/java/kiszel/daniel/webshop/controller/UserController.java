package kiszel.daniel.webshop.controller;

import kiszel.daniel.webshop.model.User;
import kiszel.daniel.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/{username}")
    public ResponseEntity<Optional<User>> getUserInformation(@PathVariable("username") String user_name){
        return new ResponseEntity<>(userService.getUser(user_name), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity editUserInformations(@RequestBody User user){
        // Edit user information
        userService.editUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
