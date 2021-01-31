package com.github.line.schedulereadonlyapi.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "grouped_daily_schedules", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class GroupedDailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "groupedDailySchedule", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClassDetails> classDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Schedule schedule;

    @Override
    public String toString() {
        return "GroupedDailySchedule{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", date=" + date +
                '}';
    }
}
