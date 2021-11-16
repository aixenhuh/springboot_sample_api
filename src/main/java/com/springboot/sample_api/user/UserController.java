package com.springboot.sample_api.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService userDaoService) {
        this.service = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> setUSer(@Valid @RequestBody User user) {
        User savedUser = this.service.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = this.service.deleteById(id);

        if (user == null) {

        }
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable int id, @PathVariable User user) {
        User updatedUser = this.service.update(id, user);
    }




}
