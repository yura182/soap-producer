package com.yura.soapproducer.service.impl;

import com.yura.soapproducer.dao.UserRepository;
import com.yura.soapproducer.dto.UserDto;
import com.yura.soapproducer.entity.UserEntity;
import com.yura.soapproducer.service.UserService;
import com.yura.soapproducer.service.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityMapper<UserEntity, UserDto> userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EntityMapper<UserEntity, UserDto> userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto add(UserDto userDto) {
        return userMapper.mapEntityToDto(saveEntity(userDto));
    }

    @Override
    public UserDto findById(Integer id) {
        return userMapper.mapEntityToDto(getUserById(id));
    }

    @Override
    public UserDto update(UserDto userDto, Integer id) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userDto.setId(id);

        return userMapper.mapEntityToDto(saveEntity(userDto));
    }

    @Override
    public void delete(Integer id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);

        userRepository.delete(userMapper.mapDtoToEntity(userDto));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    private UserEntity saveEntity(UserDto userDto) {
        return userRepository.save(userMapper.mapDtoToEntity(userDto));
    }

    private UserEntity getUserById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
