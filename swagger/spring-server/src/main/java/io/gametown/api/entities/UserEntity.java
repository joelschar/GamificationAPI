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

    public UserEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    private String email;

    @Column(name = "ACTIVE", nullable = false, insertable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active;

    @ManyToMany
    private List<BadgeStatusEntity> badgesStatus;

    @ManyToMany
    private List<PointScaleStatusEntity> pointScalesStatus;

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

    public List<PointScaleStatusEntity> getPointScalesStatus() {
        return pointScalesStatus;
    }

    public void setPointScalesStatus(List<PointScaleStatusEntity> pointScalesStatus) {
        this.pointScalesStatus = pointScalesStatus;
    }

    public List<BadgeStatusEntity> getBadgesStatus() {
        return badgesStatus;
    }

    public void setBadgesStatus(List<BadgeStatusEntity> badgesStatus) {
        this.badgesStatus = badgesStatus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
