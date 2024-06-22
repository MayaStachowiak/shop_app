//package com.example.shop.mapper;
//
//import com.example.shop.model.dao.User;
//import com.example.shop.model.dto.UserDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserMapperImpl {
//
//    public UserDto map(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .userName(user.getUserName())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail())
//                .phoneNumber(user.getPhoneNumber())
//                .build();
//    }
//
//    public User map(UserDto userDto) {
//        return User.builder()
//                .id(userDto.id())
//                .userName(userDto.userName())
//                .firstName(userDto.firstName())
//                .lastName(userDto.lastName())
//                .email(userDto.email())
//                .phoneNumber(userDto.phoneNumber())
//                .password(userDto.password())
//                .build();
//
//    }
//
//
//}
