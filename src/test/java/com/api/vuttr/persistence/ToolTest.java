package com.api.vuttr.persistence;

import com.api.vuttr.web.ToolDTO;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ToolTest {

  @Test
  void shouldCreateToolFromDTO() {
    var toolDto =
        new ToolDTO(
            "Notion",
            "https://notion.so",
            "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized.",
            Set.of("organization", "planning", "collaboration", "writing", "calendar"),
            null);
    var tool =
        new Tool(
            "Notion",
            "https://notion.so",
            "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized.",
            Set.of("organization", "planning", "collaboration", "writing", "calendar"));

    assertTrue(tool.equals(Tool.fromDTO(toolDto)));
  }
}
