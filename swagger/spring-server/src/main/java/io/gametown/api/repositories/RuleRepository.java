package io.gametown.api.repositories;

import io.gametown.api.entities.EventEntity;
import io.gametown.api.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface RuleRepository extends CrudRepository<RuleEntity, Long>{
}
