package io.gametown.api.api.service;

import io.gametown.api.api.model.*;
import io.gametown.api.entities.*;
import io.gametown.api.repositories.BadgeRepository;
import io.gametown.api.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelUtils {

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    public RuleEntity toRuleEntity(Rule rule) {
        RuleEntity entity = new RuleEntity();
        entity.setActive(rule.getActive());

        if(rule.getBadge() != null)
            entity.setBadgeEntity(toBadgeEntity(rule.getBadge()));
        else
            entity.setBadgeEntity(null);

        if(rule.getPointScale() != null)
            entity.setPointScaleEntity(toPointScaleEntity(rule.getPointScale()));
        else
            entity.setPointScaleEntity(null);

        if(rule.getNbrPoints() != null){
            entity.setNbrPoint(rule.getNbrPoints());
        }
        else {
            entity.setNbrPoint(0);
        }

        entity.setValue(rule.getValue());
        return entity;
    }

    public Rule toRule(RuleEntity entity) {
        Rule rule = new Rule();
        rule.setActive(entity.isActive());
        rule.setId((int) entity.getId());
        rule.setValue(entity.getValue());

        if(entity.getBadgeEntity() != null)
            rule.setBadge(toBadge(entity.getBadgeEntity()));
        else
            rule.setBadge(null);

        if(entity.getPointScaleEntity() != null)
            rule.setPointScale(toPointScale(entity.getPointScaleEntity()));
        else
            rule.setPointScale(null);

        rule.setNbrPoints(entity.getNbrPoint());
        return rule;
    }

    public BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        entity.setActive(badge.getActive());
        return entity;
    }

    public Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setId((int) entity.getId());
        badge.setName(entity.getName());
        badge.active(entity.isActive());
        return badge;
    }

    public PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setName(pointScale.getName());
        entity.setActive(pointScale.getActive());
        return entity;
    }

    public PointScale toPointScale(PointScaleEntity entity) {
        PointScale pointScale = new PointScale();
        pointScale.setId((int) entity.getId());
        pointScale.setName(entity.getName());
        pointScale.active(entity.isActive());
        return pointScale;
    }

    public UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setEmail(user.getEmail());
        return entity;
    }

    public User toUser(UserEntity entity) {
        User user = new User();
        user.setFirstname(entity.getFirstname());
        user.setLastname(entity.getLastname());
        user.setEmail(entity.getEmail());
        return user;
    }

    public EventEntity toEventEntity(Event event) {
        EventEntity entity = new EventEntity();
        entity.setEvent(event.getEvent());
        entity.setUserEntity(toUserEntity(event.getUser()));
        return entity;
    }

    public Event toEvent(EventEntity entity) {
        Event event = new Event();
        event.setEvent(entity.getEvent());
        return event;
    }
}
