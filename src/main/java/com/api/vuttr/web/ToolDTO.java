package com.api.vuttr.web;

import java.util.List;

public record ToolDTO(
    String title,
    String link,
    String description,
    List<String> tags,
    Integer id) {
}
