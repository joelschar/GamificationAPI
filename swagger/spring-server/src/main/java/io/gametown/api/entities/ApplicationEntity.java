package io.gametown.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class ApplicationEntity implements Serializable {

    ApplicationEntity(String apiKey){
        this.apiKey = apiKey;
    }

    @Id
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
