package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.Lecturer;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends ReadOnlyJpaRepository<Lecturer> {
}
