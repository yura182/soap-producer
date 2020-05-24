package com.yura.soapproducer.service.mapper;

import com.yura.soapproducer.dto.OrderDto;
import com.yura.soapproducer.entity.OrderEntity;
import com.yura.soapproducer.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderMapper implements EntityMapper<OrderEntity, OrderDto> {

    @Override
    public OrderDto mapEntityToDto(OrderEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setUserId(entity.getUserEntity().getId());
        orderDto.setPrice(entity.getPrice());

        return orderDto;
    }

    @Override
    public OrderEntity mapDtoToEntity(OrderDto dto) {
        return Objects.isNull(dto) ? null : OrderEntity.builder()
                .withId(dto.getId())
                .withUser(UserEntity.builder().withId(dto.getUserId()).build())
                .withPrice(dto.getPrice())
                .build();
    }
}
