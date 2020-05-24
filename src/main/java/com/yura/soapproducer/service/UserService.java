package com.yura.soapproducer.service;

import com.yura.soapproducer.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto add(UserDto userDto);

    UserDto findById(Integer id);

    List<UserDto> findAll();

    UserDto update(UserDto userDto, Integer id);

    void delete(Integer id);
}
