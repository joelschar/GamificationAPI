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
    ModelUtils tools;

    @Override
    public ResponseEntity<Event> newEvent(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                          @ApiParam(value = "" ,required=true ) @RequestBody Event event) {

     /*   ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        EventEntity eventEntity = tools.toEventEntity(event);
        eventRepository.save(eventEntity);

        String ruleToCall = eventEntity.getEvent();
        List<RuleEntity> rules = applicationEntity.getRules();
        List<RuleEntity> myRules = new ArrayList<>();
        for ( RuleEntity rule : rules ) {
            if(rule.getValue().equals(ruleToCall)){
                myRules.add(rule);
                System.out.println("rule found");
            }
        }

        if(myRules.isEmpty())
            return null;

        UserEntity myUser = eventEntity.getUserEntity();

        boolean userExists = false;
        List<UserEntity> users = applicationEntity.getUsers();
        for ( UserEntity user: users){
            if (user.getEmail().equals(myUser.getEmail())){
                userExists = true;
                myUser = user;
                System.out.println("user exists");
                break;
            }
        }

        if(!userExists){
            userRepository.save(myUser);
            users.add(myUser);
            applicationEntity.setUsers(users);
            applicationRepository.save(applicationEntity);
            System.out.println("userCreated");
        }

        for(RuleEntity myRule: myRules){
            BadgeEntity myBadge = myRule.getBadgeEntity();
            if(myBadge != null){
                BadgeStatusEntity badgeStatus = new BadgeStatusEntity();
                badgeStatus.setBadge(myBadge);
                //List<BadgeStatusEntity> badgesStatus = myUser.getBadgesStatus(); // user has no badge status
                //badgesStatus.add(badgeStatus);
                //myUser.setBadgesStatus(badgesStatus);
                System.out.println("user has new badge");
            }

            PointScaleEntity myPointScale = myRule.getPointScaleEntity();
            if(myPointScale != null){
                PointScaleStatusEntity pointScaleStatus = new PointScaleStatusEntity();
                pointScaleStatus.setPointScale(myPointScale);
                pointScaleStatus.setNbPoints(myRule.getNbrPoint());
                //List<PointScaleStatusEntity> pointsScalesStatus = myUser.getPointScalesStatus();
                //pointsScalesStatus.add(pointScaleStatus);
                //myUser.setPointScalesStatus(pointsScalesStatus);
                System.out.println("point added to user");
            }

        }

        userRepository.save(myUser);
*/
        return ResponseEntity.status(200).build();
    }

}
