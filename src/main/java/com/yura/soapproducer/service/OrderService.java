package com.yura.soapproducer.service;

import com.yura.soapproducer.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto add(OrderDto orderDto);

    OrderDto findByUserIdAndOrderId(Integer userId, Integer orderId);

    List<OrderDto> findAllByUserId(Integer userId);

    OrderDto update(OrderDto orderDto, Integer userId,  Integer orderId);

    void delete(Integer orderId);
}
