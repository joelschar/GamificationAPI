package io.gametown.api.repositories;

import io.gametown.api.entities.PointScaleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface PointScaleRepository extends CrudRepository<PointScaleEntity, Integer>{
    List<PointScaleEntity> findAllByApplication_ApiKeyAndActiveIsTrue(String apiKey);
    PointScaleEntity findByApplication_ApiKeyAndId(String apiKey, Integer id);
}
