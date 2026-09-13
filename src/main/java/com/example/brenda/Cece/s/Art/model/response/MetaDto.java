package com.example.brenda.Cece.s.Art.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaDto {
    private int statusCode;
    private String statusDescription;
    private String message;
}
