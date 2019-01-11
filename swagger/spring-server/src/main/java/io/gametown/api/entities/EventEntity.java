package io.gametown.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class EventEntity implements Serializable {

    public EventEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String event;

    private UserEntity userEntity;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ApplicationEntity application;

    public long getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }
}
