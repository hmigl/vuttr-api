package com.api.vuttr.domain;

import com.api.vuttr.persistence.Tool;
import com.api.vuttr.persistence.VuttrRepository;
import com.api.vuttr.web.ToolDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VuttrService {

  private final VuttrRepository repository;

  public VuttrService(VuttrRepository repository) {
    this.repository = repository;
  }

  public ToolDTO registerTool(ToolDTO tool) {
    return ToolDTO.fromEntity(repository.save(Tool.fromDTO(tool)));
  }

  public ResponseEntity<Page<ToolDTO>> retrieveTools(Pageable pageable) {
    List<ToolDTO> tools = repository.findAll(pageable).stream().map(ToolDTO::fromEntity).toList();
    return ResponseEntity.ok(new PageImpl<>(tools, pageable, tools.size()));
  }

  public ResponseEntity<Page<ToolDTO>> retrieveToolsByTag(String tag, Pageable pageable) {
    List<ToolDTO> tools = repository.findAllByTag(tag).stream().map(ToolDTO::fromEntity).toList();
    return ResponseEntity.ok(new PageImpl<>(tools, pageable, tools.size()));
  }
}
