package com.api.vuttr.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public record ToolDTO(
    @NotBlank String title,
    @URL String link,
    @NotBlank String description,
    @NotEmpty @UniqueElements List<String> tags,
    Integer id) {
}
