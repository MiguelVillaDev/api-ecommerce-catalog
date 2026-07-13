package com.project.catalogo.infrastructure.adapter.in.rest.mapper;

import com.project.catalogo.infrastructure.adapter.in.rest.response.CommonListResponse;
import com.project.catalogo.infrastructure.adapter.in.rest.response.CommonResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public abstract class CommonDtoMapper<M, D, R> {

    protected static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public abstract D convertToDto(M model);

    public abstract M convertToModel(R request);

    public List<D> convertToDtoList(List<M> models) {
        return models == null
                ? List.of()
                : models.stream()
                .map(this::convertToDto)
                .toList();
    }

    public CommonResponse<D> buildResponse(M model, String message) {
        return CommonResponse.<D>builder()
                .message(message)
                .response(model != null ? convertToDto(model) : null)
                .build();
    }

    public CommonListResponse<D> buildListResponse(List<M> models, String message) {
        return CommonListResponse.<D>builder()
                .message(message)
                .response(convertToDtoList(models))
                .build();
    }

    protected String formatDate(LocalDateTime date) {
        try {
            return date == null
                    ? null
                    : date.format(DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    protected Integer parseOptionalToInteger(String value) {
        try {
            return value == null || value.isBlank()
                    ? null
                    : Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected BigDecimal parseOptionalToBigDecimal(String value) {
        try {
            return value == null || value.isBlank()
                    ? null
                    : new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}