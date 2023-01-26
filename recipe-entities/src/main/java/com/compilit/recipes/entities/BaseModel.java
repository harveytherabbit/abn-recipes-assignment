package com.compilit.recipes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.Instant;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseModel {
  @Column(name = "date_created")
  private Instant dateCreated = Instant.now();

  @Column(name = "date_modified")
  private Instant dateModified = Instant.now();

  @PrePersist
  public void updateDateModified() {
    dateModified = Instant.now();
  }
}
