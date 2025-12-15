package com.task.service.dto;

public record AddressDto(Integer id,
                         String country,
                         String state,
                         String city,
                         String street,
                         String postalCode) {
}
