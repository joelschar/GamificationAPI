package io.gametown.api.api.endpoints;

import io.gametown.api.api.BadgesApi;
import io.gametown.api.api.RulesApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.PointScale;
import io.gametown.api.api.model.Rule;
import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.PointScaleEntity;
import io.gametown.api.repositories.BadgeRepository;
import io.gametown.api.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgeApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    @Override
    public ResponseEntity<Badge> createBadge(Integer apiKey, String apiSecret, Badge badge) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteBadge(Integer apiKey, String apiSecret, Badge badge) {
        return null;
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges() {
        return null;
    }

    @Override
    public ResponseEntity<Badge> updateBadge(Integer apiKey, String apiSecret, Badge badge) {
        return null;
    }

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
}
