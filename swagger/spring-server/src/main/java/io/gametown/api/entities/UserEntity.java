package io.gametown.api.entities;

import io.gametown.api.api.model.Badge;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    private String email;

    @ManyToMany
    private List<BadgeEntity> badges;

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BadgeEntity> getBadges() {
        return badges;
    }

    public void addBadgesItem(BadgeEntity badgesItem) {
        this.badges.add(badgesItem);
    }

    public void setBadges(List<BadgeEntity> badges) {
        this.badges = badges;
    }
}
