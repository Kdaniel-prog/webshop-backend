package kiszel.daniel.webshop.controller;

import kiszel.daniel.webshop.DTO.UserDTO;
import kiszel.daniel.webshop.model.User;
import kiszel.daniel.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserInformation(@PathVariable("userId") int userId){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("/userid/{username}")
    public ResponseEntity<Map<String, Integer>> getUserInformation(@PathVariable("username") String username){
        return new ResponseEntity<>(userService.getUserIdByUsername(username), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity editUserInformations(@RequestBody UserDTO user){
        userService.editUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
