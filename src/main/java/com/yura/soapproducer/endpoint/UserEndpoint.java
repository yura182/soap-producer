package com.yura.soapproducer.endpoint;

import com.yura.soapproducer.dto.*;
import com.yura.soapproducer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE = "http://dto.soapproducer.yura.com";
//    private static final ObjectFactory new ObjectFactory = new ObjectFactory();

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUserRequest(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new ObjectFactory().createGetUserResponse();

        response.setUser(userService.findById(request.getId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse addUserRequest(@RequestPayload AddUserRequest request) {
        AddUserResponse response = new ObjectFactory().createAddUserResponse();

        response.setUser(userService.add(request.getUser()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getUsersRequest")
    @ResponsePayload
    public GetUsersResponse getUsersRequest(@RequestPayload GetUsersRequest request) {
        GetUsersResponse response = new ObjectFactory().createGetUsersResponse();

        userService.findAll().forEach(user -> response.getUsers().add(user));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUserRequest(@RequestPayload UpdateUserRequest request) {
        UpdateUserResponse response = new ObjectFactory().createUpdateUserResponse();

        response.setUser(userService.update(request.getUser(), request.getUser().getId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "deleteUserRequest")
    public void deleteUserRequest(@RequestPayload DeleteUserRequest request) {
        userService.delete(request.getId());
    }
}
