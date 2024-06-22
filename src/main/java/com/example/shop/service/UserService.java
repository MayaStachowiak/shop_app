package com.example.shop.service;


import com.example.shop.model.dao.User;
import com.example.shop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Page<User> getPage(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional
    public User updateById(User user, Long id) {
        User userDb = getUser(id);
        userDb.setUserName(user.getUserName());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setEmail(user.getEmail());
        userDb.setPhoneNumber(user.getPhoneNumber());
        return userDb;
    }
}
