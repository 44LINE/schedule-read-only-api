package com.github.line.schedulereadonlyapi.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "schedules", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "version_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private ScheduleVersion scheduleVersion;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<GroupedDailySchedule> dailySchedule;

    @Column(name = "is_latest")
    private boolean isLatest;

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", isLatest=" + isLatest +
                '}';
    }
}
