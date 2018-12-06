package io.gametown.api.repositories;

import io.gametown.api.entities.ApplicationEntity;
import io.gametown.api.entities.BadgeEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, String>{

}
