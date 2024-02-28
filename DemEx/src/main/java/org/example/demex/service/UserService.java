package org.example.demex.service;

import lombok.RequiredArgsConstructor;
import org.example.demex.model.Role;
import org.example.demex.model.User;
import org.example.demex.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void registration(User user) {
        user.setFirstname(user.getFirstname());
        user.setSurname(user.getSurname());
        user.setLastname(user.getLastname());
        user.setUsername(user.getUsername());
        user.setNumber(user.getNumber());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

    }

    public User getAuthorizedUser(org.springframework.security.core.userdetails.User user) {
        return userRepo.findByUsername(user.getUsername());
    }

    public void createAdmin(){
        User user = new User();
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);
    }
}
