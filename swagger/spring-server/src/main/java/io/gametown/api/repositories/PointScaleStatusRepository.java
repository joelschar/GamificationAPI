package io.gametown.api.repositories;

import io.gametown.api.entities.PointScaleEntity;
import io.gametown.api.entities.PointScaleStatusEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface PointScaleStatusRepository extends CrudRepository<PointScaleStatusEntity, Long>{

}
