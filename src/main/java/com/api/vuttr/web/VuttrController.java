package com.api.vuttr.web;

import com.api.vuttr.domain.VuttrService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tools")
public class VuttrController {

  private final VuttrService service;

  public VuttrController(VuttrService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<ToolDTO> registerTool(@RequestBody @Valid ToolDTO tool) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.registerTool(tool));
  }

  @GetMapping
  public ResponseEntity<Page<ToolDTO>> retrieveTools(@PageableDefault Pageable pageable) {
    return service.retrieveTools(pageable);
  }
}
