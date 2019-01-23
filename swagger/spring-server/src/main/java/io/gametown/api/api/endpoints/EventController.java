package io.gametown.api.api.endpoints;

import io.gametown.api.api.BadgesApi;
import io.gametown.api.api.EventsApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.Event;
import io.gametown.api.api.model.User;
import io.gametown.api.api.service.ModelUtils;
import io.gametown.api.entities.*;
import io.gametown.api.repositories.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class EventController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeStatusRepository badgeStatusRepository;

    @Autowired
    PointScaleStatusRepository pointScaleStatusRepository;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ModelUtils tools;

    @Override
    public ResponseEntity<Event> newEvent(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                          @ApiParam(value = "" ,required=true ) @RequestBody Event event) {

        // Récupération de l'application selon l'API key
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        // Save de l'event.
        EventEntity eventEntity = tools.toEventEntity(event);
        eventRepository.save(eventEntity);

        // Récupération des règles
        List<RuleEntity> ruleEntitys = ruleRepository.findAllByApplication_ApiKeyAndValue(apiKey,eventEntity.getEvent());
        if(ruleEntitys.isEmpty())
            return null;

        //récupération du User
        UserEntity myUser = eventEntity.getUserEntity();

        //On vérifie si il existe dans la base de donnée
        UserEntity userEntity = userRepository.findByApplication_ApiKeyAndEmail(apiKey, myUser.getEmail());
        if(userEntity == null){
            userRepository.save(myUser);
            List<UserEntity> users = applicationEntity.getUsers();
            users.add(myUser);
            applicationEntity.setUsers(users);
            applicationRepository.save(applicationEntity);
            System.out.println("userCreated");
        }

        //Pour chaque règle on récupère le badge associé
        for(RuleEntity myRule: ruleEntitys){
            BadgeEntity myBadge = myRule.getBadgeEntity();
            if(myBadge != null){
                BadgeStatusEntity badgeStatusEntity = new BadgeStatusEntity();
                badgeStatusEntity.setBadge(myBadge);
                badgeStatusEntity.setUser(myUser);
                badgeStatusRepository.save(badgeStatusEntity);
                //List<BadgeStatusEntity> badgesStatus = myUser.getBadgesStatus(); // user has no badge status
                //badgesStatus.add(badgeStatus);
                //myUser.setBadgesStatus(badgesStatus);
                System.out.println("user has new badge");
            }

            PointScaleEntity myPointScale = myRule.getPointScaleEntity();
            if(myPointScale != null){
                PointScaleStatusEntity pointScaleStatusEntity = new PointScaleStatusEntity();
                pointScaleStatusEntity.setPointScale(myPointScale);
                pointScaleStatusEntity.setNbPoints(myRule.getNbrPoint());
                pointScaleStatusRepository.save(pointScaleStatusEntity);
                //List<PointScaleStatusEntity> pointsScalesStatus = myUser.getPointScalesStatus();
                //pointsScalesStatus.add(pointScaleStatus);
                //myUser.setPointScalesStatus(pointsScalesStatus);
                System.out.println("point added to user");
            }

        }

        userRepository.save(myUser);

        return ResponseEntity.status(200).build();
    }

}
