package com.yura.soapproducer.impl;

import com.yura.soapproducer.dao.UserRepository;
import com.yura.soapproducer.dto.UserDto;
import com.yura.soapproducer.entity.UserEntity;
import com.yura.soapproducer.service.impl.UserServiceImpl;
import com.yura.soapproducer.service.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final UserEntity USER_ENTITY = getUserEntity();
    private static final UserDto USER_DTO = getUserDto();
    private static final Integer ID = 1;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void add_ShouldAddUser() {
        when(userRepository.save(USER_ENTITY)).thenReturn(USER_ENTITY);
        when(userMapper.mapEntityToDto(USER_ENTITY)).thenReturn(USER_DTO);
        when(userMapper.mapDtoToEntity(USER_DTO)).thenReturn(USER_ENTITY);

        userService.add(USER_DTO);

        verify(userRepository).save(USER_ENTITY);
    }

    @Test
    void findById_ShouldReturnUser() {
        when(userRepository.findById(ID)).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.mapEntityToDto(USER_ENTITY)).thenReturn(USER_DTO);

        UserDto actual = userService.findById(ID);

        assertEquals(USER_DTO, actual);
    }

    @Test
    void findById_ShouldThrowEntityNotFoundException() {
        when(userRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findById(ID));
    }

    @Test
    void update_ShouldUpdateUser() {
        when(userRepository.findById(ID)).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.mapDtoToEntity(USER_DTO)).thenReturn(USER_ENTITY);

        userService.update(USER_DTO, ID);

        verify(userRepository).save(USER_ENTITY);
    }

    @Test
    void delete_ShouldDeleteUser() {
        when(userMapper.mapDtoToEntity(any(UserDto.class))).thenReturn(USER_ENTITY);

        userService.delete(ID);

        verify(userRepository).delete(USER_ENTITY);
    }


    private static UserEntity getUserEntity() {
        return UserEntity.builder()
                .withEmail("test@email.com")
                .withName("Name")
                .build();
    }

    private static UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@email.com");
        userDto.setName("Name");

        return userDto;
    }
}
