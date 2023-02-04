package com.api.vuttr.domain;

import com.api.vuttr.persistence.Tool;
import com.api.vuttr.persistence.VuttrRepository;
import com.api.vuttr.web.ToolDTO;
import org.springframework.stereotype.Service;

@Service
public class VuttrService {

  private final VuttrRepository repository;

  public VuttrService(VuttrRepository repository) {
    this.repository = repository;
  }

  public ToolDTO registerTool(ToolDTO tool) {
    return ToolDTO.fromEntity(repository.save(Tool.fromDTO(tool)));
  }
}
