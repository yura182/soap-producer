package com.yura.soapproducer.service.mapper;

public interface EntityMapper<E, D> {

    D mapEntityToDto(E entity);

    E mapDtoToEntity(D dto);
}
