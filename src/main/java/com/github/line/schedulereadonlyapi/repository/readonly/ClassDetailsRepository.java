package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {
}
