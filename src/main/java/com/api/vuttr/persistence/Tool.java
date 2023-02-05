package com.api.vuttr.persistence;

import com.api.vuttr.web.ToolDTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tool")
public class Tool {
  @Column(nullable = false)
  private final String title;

  @Column(nullable = false)
  private final String link;

  @Column(nullable = false)
  private final String description;

  @ElementCollection
  @CollectionTable(name = "tool_tag", joinColumns = @JoinColumn(name = "tool_id"))
  @Column(name = "tag")
  private final Set<String> tags;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  protected Tool() {
    this("", "", "", new HashSet<>());
  }

  private Tool(String title, String link, String description, Set<String> tags) {
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

  public Set<String> getTags() {
    return tags;
  }

  public Integer getId() {
    return id;
  }
}
