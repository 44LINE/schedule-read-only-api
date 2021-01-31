package com.github.line.schedulereadonlyapi.domain.api;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClassPeriod {
    public static final  DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss");

    private LocalTime startTime;
    private LocalTime endTime;
}
