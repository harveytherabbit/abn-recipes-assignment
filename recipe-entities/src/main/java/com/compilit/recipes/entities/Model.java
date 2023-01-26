package com.compilit.recipes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.Instant;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@MappedSuperclass
public class Model {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "date_created")
  private Instant dateCreated = Instant.now();

  @Column(name = "date_modified")
  private Instant dateModified = Instant.now();

  @PrePersist
  public void updateDateModified() {
    dateModified = Instant.now();
  }
}
