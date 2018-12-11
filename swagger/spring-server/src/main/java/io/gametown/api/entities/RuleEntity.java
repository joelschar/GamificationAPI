package io.gametown.api.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class RuleEntity implements Serializable {

    public RuleEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String value;
    @Column(name = "ACTIVE", columnDefinition = "BIT DEFAULT 1")
    private boolean active;

    private BadgeEntity badgeEntity;
    private PointScaleEntity pointScaleEntity;
    private int nbrPoint;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PointScaleEntity getPointScaleEntity() {
        return pointScaleEntity;
    }

    public void setPointScaleEntity(PointScaleEntity pointScaleEntity) {
        this.pointScaleEntity = pointScaleEntity;
    }

    public int getNbrPoint() {
        return nbrPoint;
    }

    public void setNbrPoint(int nbrPoint) {
        this.nbrPoint = nbrPoint;
    }

    public BadgeEntity getBadgeEntity() {
        return badgeEntity;
    }

    public void setBadgeEntity(BadgeEntity badgeEntity) {
        this.badgeEntity = badgeEntity;
    }
}
