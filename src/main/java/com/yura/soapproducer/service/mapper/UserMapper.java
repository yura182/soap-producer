package com.yura.soapproducer.service.mapper;

import com.yura.soapproducer.dto.UserDto;
import com.yura.soapproducer.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper implements EntityMapper<UserEntity, UserDto> {

    @Override
    public UserDto mapEntityToDto(UserEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        userDto.setEmail(entity.getEmail());
        userDto.setPassword(entity.getPassword());

        return userDto;
    }

    @Override
    public UserEntity mapDtoToEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : UserEntity.builder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withEmail(dto.getEmail())
                .withPassword(dto.getPassword())
                .build();
    }
}
