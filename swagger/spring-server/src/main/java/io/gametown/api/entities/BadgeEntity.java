package io.gametown.api.entities;

import io.gametown.api.api.model.Badge;
import io.swagger.models.auth.In;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class BadgeEntity implements Serializable {

    public BadgeEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ApplicationEntity application;

    @Column(name = "ACTIVE", nullable = false, insertable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }
}
