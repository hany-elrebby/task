package com.task.entity;

import java.util.List;

public interface AbstractMapper<E, D> {
    E toEntity(D d);
    D toDto(E d);

    List<E> toEntity(List<D> dtoList);
    List<D> toDto(List<E> entityList);

}
