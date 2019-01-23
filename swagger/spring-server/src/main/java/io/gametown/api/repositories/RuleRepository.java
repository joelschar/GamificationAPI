package io.gametown.api.repositories;

import io.gametown.api.entities.EventEntity;
import io.gametown.api.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface RuleRepository extends CrudRepository<RuleEntity, Integer>{

    List<RuleEntity> findAllByApplication_ApiKeyAndActiveIsTrue(String apiKey);

    RuleEntity findByApplication_ApiKeyAndId(String apiKey, Integer id);

    List<RuleEntity> findAllByApplication_ApiKeyAndValue(String apikey, String value);
}
