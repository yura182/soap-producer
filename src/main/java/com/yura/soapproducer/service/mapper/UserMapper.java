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

    private final OrderMapper orderMapper;

    @Autowired
    public UserMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

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

        entity.getOrders()
                .forEach(order->userDto.getOrders().add(orderMapper.mapEntityToDto(order)));

        return userDto;
    }

    @Override
    public UserEntity mapDtoToEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : UserEntity.builder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withEmail(dto.getEmail())
                .withPassword(dto.getPassword())
                .withOrders(Optional.ofNullable(dto.getOrders())
                        .map(Collection::stream)
                        .orElseGet(Stream::empty)
                        .map(orderMapper::mapDtoToEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
