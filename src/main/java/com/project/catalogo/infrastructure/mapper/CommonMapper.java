package com.project.catalogo.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CommonMapper<E, M> {

    public abstract M convertToModel(E entity);

    public abstract E convertToEntity(M model);

    public List<M> convertToListModel(List<E> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public List<E> convertToListEntity(List<M> models) {
        if (models == null) {
            return null;
        }

        return models.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}