package com.github.line.schedulereadonlyapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.line.schedulereadonlyapi.enums.ClassType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_details", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class ClassDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_object_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private ClassObject classObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Lecturer lecturer;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ClassType type;

    @Embedded
    private ClassPeriod classPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grouped_daily_schedule_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private GroupedDailySchedule groupedDailySchedule;

    @Override
    public String toString() {
        return "ClassDetails{" +
                "id=" + id +
                ", classObject.name='" + this.classObject.getName() + '\'' +
                ", lecturer.surname='" + this.lecturer.getSurname() + '\'' +
                ", classType='" + this.type.toString() + '\'' +
                ", classPeriod='" + this.classPeriod.getStartTime().toString() + "-" + classPeriod.getEndTime().toString() + '\'' +
                ", group='" + this.groupedDailySchedule.getGroupId() + '\'' +
                ", date='" + this.groupedDailySchedule.getDate() + '\'' +
                '}';
    }
}