package com.github.line.schedulereadonlyapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
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
    private @NonNull Long id;

    @Column(name = "group_id")
    private @NonNull Long groupId;

    @Column(name = "date")
    private @NonNull LocalDate date;

    @OneToMany(mappedBy = "groupedDailySchedule", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClassDetails> classDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private @NonNull Schedule schedule;

    @Override
    public String toString() {
        return "GroupedDailySchedule{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", date=" + date +
                '}';
    }
}
