package io.gametown.api.repositories;

import io.gametown.api.entities.EventEntity;
import io.gametown.api.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface RuleRepository extends CrudRepository<RuleEntity, Long>{

}
