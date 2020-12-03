package com.github.line.schedulereadonlyapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
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
    private @NonNull Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "version_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private @NonNull ScheduleVersion scheduleVersion;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    @JsonIgnore
    private @NonNull List<GroupedDailySchedule> dailySchedule;

    @Column(name = "is_latest")
    private @NonNull boolean isLatest;

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", isLatest=" + isLatest +
                '}';
    }
}
