package com.api.vuttr.web;

import com.api.vuttr.persistence.Tool;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ToolDTOTest {

  @Test
  void shouldCreateToolDTOfromEntity() {
    var toolDto =
        new ToolDTO(
            "Notion",
            "https://notion.so",
            "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized.",
            Set.of("organization", "planning", "collaboration", "writing", "calendar"),
            null);

    assertTrue(toolDto.equals(ToolDTO.fromEntity(Tool.fromDTO(toolDto))));
  }
}
