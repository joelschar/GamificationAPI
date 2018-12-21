package io.gametown.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

/**
 * This class is used to generate test data. Will be removed when proved useless
 */
@Configuration
public class PopulateDb {

  @Bean
  public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {

    Resource sourceData = new ClassPathResource("database/data.json");

    Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
    // Set a custom ObjectMapper if Jackson customization is needed
    factory.setResources(new Resource[] { sourceData });
    return factory;
  }
}