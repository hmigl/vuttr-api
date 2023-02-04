package com.api.vuttr.persistence;

import com.api.vuttr.web.ToolDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tool")
public class Tool {
  @Column(nullable = false)
  private final String title;

  @Column(nullable = false)
  private final String link;

  @Column(nullable = false)
  private final String description;

  @ElementCollection private final List<String> tags;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  protected Tool() {
    this("", "", "", new ArrayList<>());
  }

  private Tool(String title, String link, String description, List<String> tags) {
    this.title = title;
    this.link = link;
    this.description = description;
    this.tags = tags;
  }

  public static Tool fromDTO(ToolDTO tool) {
    return new Tool(tool.title(), tool.link(), tool.description(), tool.tags());
  }

  public String getTitle() {
    return title;
  }

  public String getLink() {
    return link;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getTags() {
    return tags;
  }

  public Integer getId() {
    return id;
  }
}
