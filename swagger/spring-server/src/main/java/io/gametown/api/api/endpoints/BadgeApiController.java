package io.gametown.api.api.endpoints;

import io.gametown.api.api.BadgesApi;
import io.gametown.api.api.RulesApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.PointScale;
import io.gametown.api.api.model.Rule;
import io.gametown.api.entities.ApplicationEntity;
import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.PointScaleEntity;
import io.gametown.api.entities.UserEntity;
import io.gametown.api.repositories.ApplicationRepository;
import io.gametown.api.repositories.BadgeRepository;
import io.gametown.api.repositories.BadgeStatusRepository;
import io.gametown.api.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgeApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    private BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        return entity;
    }

    private Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setName(entity.getName());
        return badge;
    }

    @Override
    public ResponseEntity<Badge> createBadge(String apiKey, Badge badge) {

        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<BadgeEntity> badges = applicationEntity.getBadges();

        // Create the Badge
        BadgeEntity newBadgeEntity = toBadgeEntity(badge);
        badgeRepository.save(newBadgeEntity);
        Long id = newBadgeEntity.getId();

        badges.add(newBadgeEntity);
        applicationEntity.setBadges(badges);
        applicationRepository.save(applicationEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBadgeEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteBadge(String apiKey, Badge badge) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        if(applicationEntity.getBadges().contains(toBadgeEntity(badge))) {

            BadgeEntity badgeToDelete = badgeRepository.findById(badge.getId()).orElseThrow(() -> new RuntimeException());
            badgeToDelete.setActive(false);
            badgeRepository.save(badgeToDelete);
        }

        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges(String apiKey) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<BadgeEntity> badgesEntity = applicationEntity.getBadges();

        List<Badge> badges = new ArrayList<>();

        for (BadgeEntity badgeEntity: badgesEntity ) {
            if(badgeEntity.isActive())
                badges.add(toBadge(badgeEntity));
        }

        return ResponseEntity.ok(badges);
    }

    @Override
    public ResponseEntity<Badge> updateBadge(String apiKey, Badge badge) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<BadgeEntity> badgesEntity = applicationEntity.getBadges();
        int indexOfBadge = badgesEntity.indexOf(toBadgeEntity(badge));
        badgesEntity.get(indexOfBadge).setName(badge.getName());
        applicationEntity.setBadges(badgesEntity);
        applicationRepository.save(applicationEntity);

        return ResponseEntity.status(204).build();
    }
}
