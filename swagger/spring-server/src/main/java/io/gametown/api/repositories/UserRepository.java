package io.gametown.api.repositories;

import io.gametown.api.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findByApplication_ApiKeyAndEmail(String apikey, String email);
  List<UserEntity> findAllByApplication_ApiKeyAndActiveIsTrue(String apiKey);
  UserEntity findByApplication_ApiKeyAndId(String apiKey, Integer id);
}
