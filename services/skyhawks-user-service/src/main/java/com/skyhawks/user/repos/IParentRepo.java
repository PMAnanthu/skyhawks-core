package com.skyhawks.user.repos;

import com.skyhawks.user.model.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IParentRepo extends CrudRepository<Parent, UUID> {

}
