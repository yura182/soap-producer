package com.yura.soapproducer.dao;

import com.yura.soapproducer.entity.OrderEntity;
import com.yura.soapproducer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByUserEntityAndId(UserEntity userEntity, Integer orderId);

    List<OrderEntity> findAllByUserEntity(UserEntity userEntity);
}
