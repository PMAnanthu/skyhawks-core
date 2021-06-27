package com.skyhawks.school.repos;

import com.skyhawks.school.model.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISchoolRepo extends CrudRepository<School, UUID> {

    @Query("From School Where schoolCode = :schoolCode")
    School findBySchoolCode(String schoolCode);
}
