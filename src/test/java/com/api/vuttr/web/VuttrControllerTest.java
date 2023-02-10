package com.api.vuttr.web;

import com.api.vuttr.domain.VuttrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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
  void retrieveTools() {}

  @Test
  void deleteToolById() {}
}
