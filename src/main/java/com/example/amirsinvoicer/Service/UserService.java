package com.example.amirsinvoicer.Service;

import com.example.amirsinvoicer.Model.User;
import com.example.amirsinvoicer.Repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔑 Verplicht: gebruiker ophalen voor Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Gebruiker niet gevonden"));
        System.out.println("Login check:" + username);
        System.out.println("Opgehaald wachtwoord (bcrypt): " + user.getPassword());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }

    // CRUD operaties
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void insertUser(User user) {
        System.out.println("🔍 Inserting user:");
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("role: " + user.getRole());

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
