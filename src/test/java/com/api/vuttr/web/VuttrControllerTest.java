package com.api.vuttr.web;

import com.api.vuttr.domain.VuttrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VuttrController.class)
class VuttrControllerTest {
  private static final String URI = "/api/v1/tools";

  @Autowired private MockMvc mockMvc;

  @MockBean private VuttrService vuttrService;

  @Test
  void shouldRegisterTool() throws Exception {
    given(vuttrService.registerTool(any(ToolDTO.class)))
        .willReturn(ResponseEntity.status(HttpStatus.CREATED).body(any(ToolDTO.class)));

    mockMvc
        .perform(
            post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    new ObjectMapper()
                        .writeValueAsString(
                            new ToolDTO(
                                "fastify",
                                "https://www.fastify.io/",
                                "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.",
                                Set.of("web", "framework", "node", "http2", "https", "localhost"),
                                null))))
        .andExpect(status().isCreated());

    verify(vuttrService, times(1)).registerTool(any(ToolDTO.class));
  }

  @Test
  void retrieveTools() throws Exception {
    List<ToolDTO> tools =
        List.of(
            new ToolDTO(
                "fastify",
                "https://www.fastify.io/",
                "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.",
                Set.of("web", "framework", "node", "http2", "https", "localhost"),
                1),
            new ToolDTO(
                "Notion",
                "https://notion.so",
                "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized.",
                Set.of("organization", "planning", "collaboration", "writing", "calendar"),
                2));

    given(vuttrService.retrieveTools(any(Pageable.class)))
        .willReturn(ResponseEntity.ok(new PageImpl<>(tools, Pageable.ofSize(2), tools.size())));

    mockMvc.perform(get(URI)).andExpect(status().isOk());
    verify(vuttrService, times(1)).retrieveTools(any(Pageable.class));
    verify(vuttrService, times(0)).retrieveToolsByTag(any(String.class), any(Pageable.class));
  }

  @Test
  void retrieveToolsByTag() throws Exception {
    List<ToolDTO> tools =
        List.of(
            new ToolDTO(
                "fastify",
                "https://www.fastify.io/",
                "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.",
                Set.of("web", "framework", "node", "http2", "https", "localhost"),
                1),
            new ToolDTO(
                "json-server",
                "https://github.com/typicode/json-server",
                "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.",
                Set.of("api", "json", "schema", "node", "github", "rest"),
                2));

    given(vuttrService.retrieveToolsByTag(any(String.class), any(Pageable.class)))
        .willReturn(ResponseEntity.ok(new PageImpl<>(tools)));

    mockMvc
        .perform(get(URI).param("tag", "node"))
        .andExpect(status().isOk()); // TODO: improve with jsonpath

    verify(vuttrService, times(1)).retrieveToolsByTag(any(String.class), any(Pageable.class));
    verify(vuttrService, times(0)).retrieveTools(any(Pageable.class));
  }

  @Test
  void deleteToolById() {}
}
