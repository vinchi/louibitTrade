package kr.nexparan.louibitTrade.controller;

import kr.nexparan.louibitTrade.model.User;
import kr.nexparan.louibitTrade.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    List<User> all() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Single item

    @GetMapping("/user/{id}")
    User one(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/user/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(user -> {
//                    user.setTitle(newUser.getTitle());
//                    user.setContent(newUser.getContent());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
