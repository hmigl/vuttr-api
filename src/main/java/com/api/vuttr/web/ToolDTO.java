package com.api.vuttr.web;

import com.api.vuttr.persistence.Tool;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

public record ToolDTO(
    @NotBlank String title,
    @URL String link,
    @NotBlank String description,
    @NotEmpty Set<String> tags,
    Integer id) {
  public static ToolDTO fromEntity(Tool tool) {
    return new ToolDTO(
        tool.getTitle(), tool.getLink(), tool.getDescription(), tool.getTags(), tool.getId());
  }
}
