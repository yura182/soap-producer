package com.yura.soapproducer.service.impl;

import com.yura.soapproducer.dao.OrderRepository;
import com.yura.soapproducer.dto.OrderDto;
import com.yura.soapproducer.entity.OrderEntity;
import com.yura.soapproducer.entity.UserEntity;
import com.yura.soapproducer.service.OrderService;
import com.yura.soapproducer.service.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EntityMapper<OrderEntity, OrderDto> orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EntityMapper<OrderEntity, OrderDto> orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto add(OrderDto orderDto) {
        return orderMapper.mapEntityToDto(saveEntity(orderDto));
    }

    @Override
    public OrderDto findByUserIdAndOrderId(Integer userId, Integer orderId) {
        return orderMapper.mapEntityToDto(getOrderByUserIdAndOrderId(userId, orderId));
    }

    @Override
    public List<OrderDto> findAllByUserId(Integer userId) {
        return orderRepository.findAllByUserEntity(getUserEntityWithId(userId))
                .stream()
                .map(orderMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto update(OrderDto orderDto, Integer userId, Integer orderId) {
        orderRepository
                .findByUserEntityAndId(getUserEntityWithId(userId), orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        orderDto.setId(orderId);
        orderDto.setUserId(userId);

        return orderMapper.mapEntityToDto(saveEntity(orderDto));
    }

    @Override
    public void delete(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    private UserEntity getUserEntityWithId(Integer userId) {
        return UserEntity.builder().withId(userId).build();
    }

    private OrderEntity saveEntity(OrderDto orderDto) {
        return orderRepository.save(orderMapper.mapDtoToEntity(orderDto));
    }

    private OrderEntity getOrderByUserIdAndOrderId(Integer userId, Integer orderId) {
        return orderRepository
                .findByUserEntityAndId(getUserEntityWithId(userId), orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }
}
