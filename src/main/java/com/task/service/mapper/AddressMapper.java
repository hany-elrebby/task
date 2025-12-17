package com.task.service.mapper;

import com.task.entity.Address;
import com.task.service.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends AbstractMapper<Address, AddressDto> {
}
