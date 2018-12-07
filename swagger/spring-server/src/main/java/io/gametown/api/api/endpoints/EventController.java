package io.gametown.api.api.endpoints;

import io.gametown.api.api.BadgesApi;
import io.gametown.api.api.EventsApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.Event;
import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.EventEntity;
import io.gametown.api.repositories.EventRepository;
import io.gametown.api.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class EventController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    private EventEntity toEventEntity(Event event) {
        EventEntity entity = new EventEntity();
        entity.setEvent(event.getEvent());
        return entity;
    }

    private Event toEvent(EventEntity entity) {
        Event event = new Event();
        event.setEvent(entity.getEvent());
        return event;
    }

    @Override
    public ResponseEntity<Event> newEvent(String apiKey, Event event) {
        return null;
    }
}
