package io.gametown.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class PointScaleEntity implements Serializable {

    public PointScaleEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private boolean active;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
