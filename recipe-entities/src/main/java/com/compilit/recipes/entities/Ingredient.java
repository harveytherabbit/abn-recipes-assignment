package com.compilit.recipes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient extends BaseModel {

  @Id
  @Column(name = "name")
  private String name;
  @Column(name = "category")
  @Enumerated(EnumType.STRING)
  private Category category;

}
