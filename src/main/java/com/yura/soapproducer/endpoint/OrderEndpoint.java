package com.yura.soapproducer.endpoint;

import com.yura.soapproducer.dto.*;
import com.yura.soapproducer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class OrderEndpoint {
    private static final String NAMESPACE = "http://dto.soapproducer.yura.com";
    private static final ObjectFactory FACTORY = new ObjectFactory();

    private final OrderService orderService;

    @Autowired
    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrderRequest(@RequestPayload GetOrderRequest request) {
        GetOrderResponse response = FACTORY.createGetOrderResponse();

        OrderDto orderDto = orderService.findByUserIdAndOrderId(request.getUserId(), request.getId());
        response.setOrder(orderDto);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "addOrderRequest")
    @ResponsePayload
    public AddOrderResponse addOrderRequest(@RequestPayload AddOrderRequest request) {
        AddOrderResponse response = FACTORY.createAddOrderResponse();

        OrderDto orderDto = orderService.add(request.getOrder());
        response.setOrder(orderDto);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllOrdersOfUserRequest")
    @ResponsePayload
    public GetAllOrdersOfUserResponse getOrdersRequest(@RequestPayload GetAllOrdersOfUserRequest request) {
        GetAllOrdersOfUserResponse response = FACTORY.createGetAllOrdersOfUserResponse();

        orderService.findAllByUserId(request.getUserId()).forEach(order->response.getOrders().add(order));

        return response;
    }

}
