package com.llm.walraimer.core.controller;

import com.llm.walraimer.core.dto.UserDTO;
import com.llm.walraimer.core.entity.User;
import com.llm.walraimer.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/view-all")
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user  = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO){
        User user = userService.fromDTO(userDTO);
        User createdUser = userService.create(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(name = "/update/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        User updatedUser = userService.update(id, user);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
