package com.project.taskTracker.repo;

import com.project.taskTracker.models.StudyDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface StudyDayRepo extends JpaRepository<StudyDay,Long> {
    Optional<StudyDay> findByDate(LocalDate date);
    List<StudyDay> findByMonthAndYearOrderByDateAsc(int month , int year);

    @Query("""
            select distinct sd.month , sd.year 
            from StudyDay sd
            order by sd.year desc , sd.month desc
            """
    )
    List<Object[]>findAvailableMonths();

    @Query("""
            select sd 
            from StudyDay sd
            where sd.month = :month and sd.year = :year
            order by sd.weekOfMonth , sd.date
            """)
    List<StudyDay> findMonthGroupedByWeek(@Param("month") int month,
                                          @Param("year") int year);

}
