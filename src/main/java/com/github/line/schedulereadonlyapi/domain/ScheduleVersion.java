package com.github.line.schedulereadonlyapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule_versions", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "url", "addition_date"}))
public class ScheduleVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "addition_date")
    private LocalDateTime additionDate;

    @OneToOne(mappedBy = "scheduleVersion", fetch = FetchType.LAZY)
    @JsonIgnore
    private Schedule schedule;

    public ScheduleVersion() {
    }

    public ScheduleVersion(Long id, String url, LocalDateTime updateDate, LocalDateTime additionDate, Schedule schedule) {
        this.id = id;
        this.url = url;
        this.updateDate = updateDate;
        this.additionDate = additionDate;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(LocalDateTime additionDate) {
        this.additionDate = additionDate;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
