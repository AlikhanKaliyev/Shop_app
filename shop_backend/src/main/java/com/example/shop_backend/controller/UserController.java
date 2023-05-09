package com.example.shop_backend.controller;

import com.example.shop_backend.domain.model.error.ErrorResponse;
import com.example.shop_backend.domain.model.user.User;
import com.example.shop_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        try {
            User _user = userRepository.save(
                    user
            );
            return new ResponseEntity(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse("Some properties are missing"),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("users")
    public ResponseEntity getUsers() {
        try {
            List<User> users = new ArrayList<User>();
            userRepository.findAll().forEach(users::add);
            return new ResponseEntity(users,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
