package com.api.vuttr.persistence;

import com.api.vuttr.web.ToolDTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tool")
public class Tool {
  @Column(nullable = false, unique = true)
  private final String title;

  @Column(nullable = false, unique = true)
  private final String link;

  @Column(nullable = false)
  private final String description;

  @ElementCollection
  @CollectionTable(name = "tool_tag", joinColumns = @JoinColumn(name = "tool_id"))
  @Column(name = "tag")
  private final Set<String> tags;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  protected Tool() {
    this("", "", "", new HashSet<>());
  }

  protected Tool(String title, String link, String description, Set<String> tags) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tool tool = (Tool) o;
    return title.equals(tool.title)
        && link.equals(tool.link)
        && description.equals(tool.description)
        && tags.equals(tool.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, link, description, tags);
  }
}
