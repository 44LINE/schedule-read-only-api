package com.github.line.schedulereadonlyapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.line.schedulereadonlyapi.enums.ClassType;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "class_details", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class ClassDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_object_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private @NonNull ClassObject classObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private @NonNull Lecturer lecturer;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private @NonNull ClassType type;

    @Embedded
    private @NonNull ClassPeriod classPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grouped_daily_schedule_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private @NonNull GroupedDailySchedule groupedDailySchedule;

    @Override
    public String toString() {
        return "ClassDetails{" +
                "id=" + id +
                ", classObject=" + classObject +
                ", lecturer=" + lecturer +
                ", type=" + type +
                ", classPeriod=" + classPeriod +
                '}';
    }
}