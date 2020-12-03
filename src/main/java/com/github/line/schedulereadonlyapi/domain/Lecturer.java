package com.github.line.schedulereadonlyapi.domain;

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
    private @NonNull Long id;

    @Column(name = "name")
    private @NonNull String name;

    @Column(name = "surname")
    private @NonNull String surname;

    @Column(name = "shortname")
    private @NonNull String shortName;

    @Column(name = "email")
    private String email;
}
