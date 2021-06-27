package com.skyhawks.user.repos;

import com.skyhawks.user.model.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStaffRepo extends CrudRepository<Staff, UUID> {

}
