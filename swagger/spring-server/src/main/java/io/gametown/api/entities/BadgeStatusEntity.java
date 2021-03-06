package io.gametown.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class BadgeStatusEntity implements Serializable {

    public BadgeStatusEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "REGISTRATION_TIMESTAMP", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp registrationTimestamp;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.ALL)
    private BadgeEntity badge;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.ALL)
    private UserEntity user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public BadgeEntity getBadge() {
        return badge;
    }

    public void setBadge(BadgeEntity badge) {
        this.badge = badge;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
