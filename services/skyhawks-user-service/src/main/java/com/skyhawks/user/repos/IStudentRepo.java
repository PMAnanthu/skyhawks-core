package com.skyhawks.user.repos;

import com.skyhawks.user.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStudentRepo extends CrudRepository<Student, UUID> {

}
