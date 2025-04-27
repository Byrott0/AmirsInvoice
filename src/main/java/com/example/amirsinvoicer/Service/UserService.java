package com.example.amirsinvoicer.Service;

import com.example.amirsinvoicer.Model.User;
import com.example.amirsinvoicer.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void insertUser(User user) {
        user.setId(null);
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(UUID id, User update) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));

        user.setUsername(update.getUsername());
        user.setEmail(update.getEmail());
        user.setPhoneNumber(update.getPhoneNumber());
        user.setEmail(update.getEmail());
        userRepository.save(user);
    }
}
