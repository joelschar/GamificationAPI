package io.gametown.api.repositories;

import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.BadgeStatusEntity;
import io.gametown.api.entities.RuleEntity;
import io.gametown.api.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface BadgeStatusRepository extends CrudRepository<BadgeStatusEntity, Long>{
    List<BadgeStatusEntity> findAllByApplication_ApiKeyAndUser(String apikey, UserEntity user);
}
