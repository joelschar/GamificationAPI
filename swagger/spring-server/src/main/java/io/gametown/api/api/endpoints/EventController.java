package io.gametown.api.api.endpoints;

import io.gametown.api.api.BadgesApi;
import io.gametown.api.api.EventsApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.Event;
import io.gametown.api.api.model.User;
import io.gametown.api.entities.*;
import io.gametown.api.repositories.ApplicationRepository;
import io.gametown.api.repositories.EventRepository;
import io.gametown.api.repositories.PointScaleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class EventController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<Event> newEvent(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                          @ApiParam(value = "" ,required=true ) @RequestBody Event event) {
        /*
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        EventEntity eventEntity = toEventEntity(event);
        eventRepository.save(eventEntity);

        for ( RuleEntity ruleEntity: applicationEntity.getRules()) {
            if(ruleEntity.getValue().equals(event.getEvent())){
                ruleEntity.getKondition();
            }
        }
        */
        return null;
    }
}
