package com.example.amirsinvoicer.Controller;
import com.example.amirsinvoicer.Model.User;
import com.example.amirsinvoicer.Service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    //constructor verwijst naar de UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // haal alle users op
    @GetMapping
    List<User> getAllUser() {
        return userService.getAllUsers();
    }
    // zoek user op UUID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }
    // maak een User aan
    @PostMapping
    public void AddUser (@RequestBody User user) {
        userService.insertUser(user);
    }
    // delete user
    @DeleteMapping
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }
    // update user
    @PutMapping("/{id}")
    public void updateUser(@PathVariable UUID id,
                           @RequestBody User user) {
        userService.updateUser(id, user);
    }

}
