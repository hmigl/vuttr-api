package com.api.vuttr.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VuttrRepository extends JpaRepository<Tool, Integer> {
  @Query("SELECT t FROM Tool t JOIN t.tags tg WHERE tg = :tag")
  List<Tool> findAllByTag(@Param("tag") String tag);
}
