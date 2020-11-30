package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.ClassObject;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassObjectRepository extends ReadOnlyJpaRepository<ClassObject> {
}
