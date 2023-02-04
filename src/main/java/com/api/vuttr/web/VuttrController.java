package com.api.vuttr.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tools")
public class VuttrController {

  @PostMapping
  public ResponseEntity<ToolDTO> registerTool(@RequestBody @Valid ToolDTO tool) {
    return ResponseEntity.status(HttpStatus.CREATED).body(tool);
  }
}
