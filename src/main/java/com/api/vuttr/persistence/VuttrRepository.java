package com.api.vuttr.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VuttrRepository extends JpaRepository<Tool, Integer> {}