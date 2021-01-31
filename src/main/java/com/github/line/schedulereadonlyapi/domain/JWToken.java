package com.github.line.schedulereadonlyapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "auth_token")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JWToken implements Serializable {
    @Id
    private String token;
    private String username;
}
