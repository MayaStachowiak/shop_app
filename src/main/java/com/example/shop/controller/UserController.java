package com.example.shop.controller;


import com.example.shop.mapper.UserMapper;
import com.example.shop.model.dao.User;
import com.example.shop.model.dto.UserDto;
import com.example.shop.service.UserService;
import com.example.shop.validator.group.Create;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.map(userService.getUser(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(Create.class)
    public UserDto saveUser(@RequestBody @Valid UserDto user) {
        User userToSave = userService.save(userMapper.map(user));
        return userMapper.map(userToSave);
    }

    @PutMapping("/{id}")
    public UserDto updateUserById(@RequestBody UserDto user, @PathVariable Long id) {
        return userMapper.map(userService.updateById(userMapper.map(user), id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }


    @GetMapping("/all")
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {
        Page<User> users = userService.getPage(page, size);
        return users.map(userMapper::map);
    }

}
