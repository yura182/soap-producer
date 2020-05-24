package com.yura.soapproducer.service.mapper;

import com.yura.soapproducer.dto.UserDto;
import com.yura.soapproducer.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserMapper implements EntityMapper<UserEntity, UserDto> {

    @Override
    public UserDto mapEntityToDto(UserEntity entity) {
        return Objects.isNull(entity) ? null : UserDto.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .build();
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
