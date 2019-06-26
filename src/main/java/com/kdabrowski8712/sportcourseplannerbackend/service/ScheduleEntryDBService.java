package com.kdabrowski8712.sportcourseplannerbackend.service;

import com.kdabrowski8712.sportcourseplannerbackend.domain.ScheduleEntry;
import com.kdabrowski8712.sportcourseplannerbackend.repository.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleEntryDBService {

    @Autowired
    private ScheduleDao scheduleDao;

    public Optional<ScheduleEntry> getScheduleEntry(Long id) {
        return scheduleDao.findById(id);
    }

    public ScheduleEntry saveEntry(ScheduleEntry scheduleEntry) {
        return scheduleDao.save(scheduleEntry);
    }

    public void deleteEntry(Long id) {
        scheduleDao.deleteById(id);
    }

}
