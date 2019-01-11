package io.gametown.api.api.service;

import io.gametown.api.api.model.*;
import io.gametown.api.entities.*;
import org.springframework.stereotype.Service;

@Service
public class ModelUtils {

    public RuleEntity toRuleEntity(Rule rule) {
        RuleEntity entity = new RuleEntity();
        entity.setBadgeEntity(toBadgeEntity(rule.getBadge()));
        entity.setNbrPoint(rule.getNbrPoints());
        entity.setPointScaleEntity(toPointScaleEntity(rule.getPointScale()));
        entity.setValue(rule.getValue());
        return entity;
    }

    public Rule toRule(RuleEntity entity) {
        Rule rule = new Rule();
        rule.setId((int) entity.getId());
        rule.setValue(entity.getValue());
        rule.setBadge(toBadge(entity.getBadgeEntity()));
        rule.setNbrPoints(entity.getNbrPoint());
        rule.setPointScale(toPointScale(entity.getPointScaleEntity()));
        return rule;
    }

    public BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        return entity;
    }

    public Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setId((int) entity.getId());
        badge.setName(entity.getName());
        return badge;
    }

    public PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setName(pointScale.getName());
        return entity;
    }

    public PointScale toPointScale(PointScaleEntity entity) {
        PointScale pointScale = new PointScale();
        pointScale.setId((int) entity.getId());
        pointScale.setName(entity.getName());
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
