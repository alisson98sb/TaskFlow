package com.example.taskFlow.service;

import com.example.taskFlow.entity.User;
import com.example.taskFlow.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() { return userRepository.findAll(); }

    public List<User> create(User user) {
        userRepository.save(user);
        return list();
    }

    public List<User> update(Long id, User user){
        userRepository.findById(id).ifPresentOrElse( (existingUser) -> {
            user.setId(id);
            userRepository.save(user);
        },  () -> {
            try {
                throw new BadRequestException("User %d não existe! ".formatted(id));
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        } );

        return list();
    }

    public List<User> delete(Long id) {
        userRepository.findById(id).ifPresentOrElse( (existingUser) -> userRepository.delete(existingUser) , () -> {
            try {
                throw new BadRequestException("Todo %d não existe! ".formatted(id));
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });

        return list();
    }
}
