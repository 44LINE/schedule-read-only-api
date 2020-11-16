package com.github.line.schedulereadonlyapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "grouped_daily_schedules", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class GroupedDailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_id")
    private long groupId;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "groupedDailySchedule", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClassDetails> classDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Schedule schedule;

    public GroupedDailySchedule() {
    }

    public GroupedDailySchedule(Long id, long groupId, LocalDate date, List<ClassDetails> classDetails, Schedule schedule) {
        this.id = id;
        this.groupId = groupId;
        this.date = date;
        this.classDetails = classDetails;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ClassDetails> getClassDetails() {
        return Collections.unmodifiableList(classDetails);
    }

    public void setClassDetails(List<ClassDetails> classDetails) {
        this.classDetails = classDetails;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
