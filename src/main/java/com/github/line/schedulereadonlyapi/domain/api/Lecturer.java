package com.github.line.schedulereadonlyapi.domain.api;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "lecturers", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "surname", "shortname"}))
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "shortname")
    private String shortName;

    @Column(name = "email")
    private String email;
}
