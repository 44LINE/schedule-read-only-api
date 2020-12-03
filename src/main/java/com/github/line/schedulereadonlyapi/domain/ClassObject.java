package com.github.line.schedulereadonlyapi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "class_objects", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class ClassObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private @NonNull Long id;

    @Column(name = "name")
    private @NonNull String name;

    @Column(name = "short_name")
    private @NonNull String shortName;
}
