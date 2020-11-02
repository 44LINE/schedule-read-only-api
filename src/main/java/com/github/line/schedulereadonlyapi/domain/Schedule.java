package com.github.line.schedulereadonlyapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
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

    public Schedule() {
    }

    public Schedule(Long id, ScheduleVersion scheduleVersion, List<GroupedDailySchedule> dailySchedule, boolean isLatest) {
        this.id = id;
        this.scheduleVersion = scheduleVersion;
        this.dailySchedule = dailySchedule;
        this.isLatest = isLatest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScheduleVersion getScheduleVersion() {
        return scheduleVersion;
    }

    public void setScheduleVersion(ScheduleVersion scheduleVersion) {
        this.scheduleVersion = scheduleVersion;
    }

    public List<GroupedDailySchedule> getDailySchedule() {
        return Collections.unmodifiableList(dailySchedule);
    }

    public void setDailySchedule(List<GroupedDailySchedule> dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    public boolean isLatest() {
        return isLatest;
    }

    public void setLatest(boolean latest) {
        isLatest = latest;
    }
}
