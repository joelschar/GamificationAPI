package io.gametown.api.entities;

import io.gametown.api.api.model.PointScale;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class ApplicationEntity implements Serializable {

    public ApplicationEntity() {}

    public ApplicationEntity(String apiKey){
        this.apiKey = apiKey;
    }

    @Id
    private String apiKey;

    @OneToMany(targetEntity = BadgeEntity.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "application")
    private List<BadgeEntity> badges;

    @OneToMany(targetEntity = RuleEntity.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "application")
    private List<RuleEntity> rules;

    @OneToMany(targetEntity = PointScaleEntity.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "application")
    private List<PointScaleEntity> pointScales;

    @OneToMany(targetEntity = UserEntity.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "application")
    private List<UserEntity> users;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<BadgeEntity> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeEntity> badges) {
        this.badges = badges;
    }

    public List<RuleEntity> getRules() {
        return rules;
    }

    public void setRules(List<RuleEntity> rules) {
        this.rules = rules;
    }

    public List<PointScaleEntity> getPointScales() {
        return pointScales;
    }

    public void setPointScales(List<PointScaleEntity> pointScales) {
        this.pointScales = pointScales;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
