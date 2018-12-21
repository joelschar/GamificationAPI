package io.gametown.api.api.endpoints;

import io.gametown.api.api.BadgesApi;
import io.gametown.api.api.RulesApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.PointScale;
import io.gametown.api.api.model.Rule;
import io.gametown.api.api.service.ModelUtils;
import io.gametown.api.entities.ApplicationEntity;
import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.PointScaleEntity;
import io.gametown.api.entities.UserEntity;
import io.gametown.api.repositories.ApplicationRepository;
import io.gametown.api.repositories.BadgeRepository;
import io.gametown.api.repositories.BadgeStatusRepository;
import io.gametown.api.repositories.PointScaleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @Autowired
    ModelUtils tools;

    @Override
    public ResponseEntity<Badge> createBadge(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                             @ApiParam(value = "" ,required=true ) @RequestBody Badge badge) {

        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<BadgeEntity> badges = applicationEntity.getBadges();

        // Create the Badge
        BadgeEntity newBadgeEntity = tools.toBadgeEntity(badge);
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
    public ResponseEntity<Void> deleteBadge(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                            @ApiParam(value = ""  ) @RequestBody Badge badge) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<BadgeEntity> badgesEntity = applicationEntity.getBadges();
        for (BadgeEntity badgeEntity: badgesEntity ) {
            if(badgeEntity.getId() == badge.getId()){
                BadgeEntity badgeToDelete = badgeEntity;
                badgeToDelete.setActive(false);
                badgeRepository.save(badgeToDelete);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey){
        System.out.println(apiKey);
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<BadgeEntity> badgesEntity = applicationEntity.getBadges();

        List<Badge> badges = new ArrayList<>();

        for (BadgeEntity badgeEntity: badgesEntity ) {
            if(badgeEntity.isActive())
                badges.add(tools.toBadge(badgeEntity));
        }

        return ResponseEntity.ok(badges);
    }

    @Override
    public ResponseEntity<Badge> updateBadge(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                             @ApiParam(value = "" ,required=true ) @RequestBody Badge badge) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<BadgeEntity> badgesEntity = applicationEntity.getBadges();
        for (BadgeEntity badgeEntity: badgesEntity ) {
            if(badgeEntity.getId() == badge.getId()){
                BadgeEntity badgeToUpdate = badgeEntity;
                badgeToUpdate.setActive(true);
                badgeToUpdate.setName(badge.getName());
                badgeRepository.save(badgeToUpdate);
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
