package com.github.line.schedulereadonlyapi.domain;

import com.github.line.schedulereadonlyapi.enums.DayTimePeriods;
import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClassPeriod {

    private @NonNull LocalTime startTime;
    private @NonNull LocalTime endTime;
}
