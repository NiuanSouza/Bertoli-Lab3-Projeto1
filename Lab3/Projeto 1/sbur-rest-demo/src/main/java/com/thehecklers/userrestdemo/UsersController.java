package com.thehecklers.userrestdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")
class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;

        if (userRepository.count() == 0) {
            userRepository.saveAll(List.of(
                    new Users("Carlos Silva", "carlos.silva@email.com"),
                    new Users("Ana Souza", "ana.souza@email.com")
            ));
        }
    }

    @GetMapping
    Iterable<Users> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Users> getUserById(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @PostMapping
    Users postUser(@RequestBody Users user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(java.util.UUID.randomUUID().toString());
        }
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    ResponseEntity<Users> putUser(@PathVariable String id, @RequestBody Users user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }
}
