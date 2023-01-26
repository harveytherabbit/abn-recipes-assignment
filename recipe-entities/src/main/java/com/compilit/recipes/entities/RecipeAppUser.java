package com.compilit.recipes.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class RecipeAppUser extends BaseModel implements UserDetails {

  @Getter
  @Id
  @Column(name = "username")
  private String username;
  @Getter
  @Column(name = "password")
  private String password;
  @Getter
  @OneToMany(targetEntity = Authority.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Collection<GrantedAuthority> authorities;
  @Getter
  private boolean accountNonExpired;
  @Getter
  private boolean accountNonLocked;
  @Getter
  private boolean credentialsNonExpired;
  @Getter
  private boolean enabled;

  @Getter
  @OneToMany(targetEntity = Recipe.class)
  private List<Recipe> recipes = new ArrayList<>();

}
