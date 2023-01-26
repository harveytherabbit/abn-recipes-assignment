package com.compilit.recipes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Authority extends Model implements GrantedAuthority {

    @Column(name = "authority")
    private String authority;

}
