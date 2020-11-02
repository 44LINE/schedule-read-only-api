package com.github.line.schedulereadonlyapi.config;

import com.github.line.schedulereadonlyapi.controller.*;
import com.github.line.schedulereadonlyapi.hateoas.*;
import com.github.line.schedulereadonlyapi.repository.readonly.*;
import com.github.line.schedulereadonlyapi.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class BeanConfig {

    //repos
    @Resource
    private ClassDetailsRepository classDetailsRepository;
    @Resource
    private ClassObjectRepository classObjectRepository;
    @Resource
    private GroupedDailyScheduleRepository groupedDailyScheduleRepository;
    @Resource
    private LecturerRepository lecturerRepository;
    @Resource
    private ScheduleRepository scheduleRepository;
    @Resource
    private ScheduleVersionRepository scheduleVersionRepository;

    //hateoas assemblers
    @Bean
    public ClassObjectAssembler classObjectAssembler() {
        return new ClassObjectAssembler();
    }
    @Bean
    public ClassDetailsAssembler classDetailsAssembler() {
        return new ClassDetailsAssembler();
    }
    @Bean
    public GroupedDailyScheduleAssembler groupedDailyScheduleAssembler() {
        return new GroupedDailyScheduleAssembler();
    }
    @Bean
    public LecturerAssembler lecturerAssembler() {
        return new LecturerAssembler();
    }
    @Bean
    public ScheduleAssembler scheduleAssembler() {
        return new ScheduleAssembler();
    }
    @Bean
    public ScheduleVersionAssembler scheduleVersionAssembler() {
        return new ScheduleVersionAssembler();
    }

    //services
    @Bean
    public ClassObjectService classObjectService() {
        return new ClassObjectService(classObjectRepository, classObjectAssembler());
    }
    @Bean
    public LecturerService lecturerService() {
        return new LecturerService(lecturerRepository, lecturerAssembler());
    }
    @Bean
    public GroupedDailyScheduleService groupedDailyScheduleService() {
        return new GroupedDailyScheduleService(groupedDailyScheduleRepository, groupedDailyScheduleAssembler());
    }
    @Bean
    public ScheduleVersionService scheduleVersionService() {
        return new ScheduleVersionService(scheduleVersionRepository, scheduleVersionAssembler());
    }
    @Bean
    public ScheduleService scheduleService() {
        return new ScheduleService(scheduleRepository, scheduleAssembler());
    }
    @Bean
    public ClassDetailsService classDetailsService() {
        return new ClassDetailsService(classDetailsRepository, classDetailsAssembler());
    }

    //controllers
    @Bean
    public LecturerController lecturerController() {
        return new LecturerController(lecturerService());
    }
    @Bean
    public ClassObjectController classObjectController() {
        return new ClassObjectController(classObjectService());
    }
    @Bean
    public ClassDetailsController classDetailsController() {
        return new ClassDetailsController(classDetailsService());
    }
    @Bean
    public GroupedDailyScheduleController groupedDailyScheduleController() {
        return new GroupedDailyScheduleController(groupedDailyScheduleService());
    }
    @Bean
    public ScheduleVersionController scheduleVersionController() {
        return new ScheduleVersionController(scheduleVersionService());
    }
    @Bean
    public ScheduleController scheduleController() {
        return new ScheduleController(scheduleService());
    }
}
