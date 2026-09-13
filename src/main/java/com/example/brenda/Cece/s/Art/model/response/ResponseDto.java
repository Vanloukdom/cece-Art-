package com.example.brenda.Cece.s.Art.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Serializable {
    private MetaDto meta;
    private Object data;
    private String errors;
    private PaginationDto pagination;
}

