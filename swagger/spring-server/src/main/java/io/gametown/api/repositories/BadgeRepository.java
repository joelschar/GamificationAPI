package io.gametown.api.repositories;

import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface BadgeRepository extends CrudRepository<BadgeEntity, Integer>{

}
