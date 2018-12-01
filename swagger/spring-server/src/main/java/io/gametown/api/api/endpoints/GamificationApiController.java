package io.gametown.api.api.endpoints;

import io.gametown.api.api.UsersApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.User;
import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.UserEntity;
import io.gametown.api.repositories.BadgeRepository;
import io.gametown.api.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class GamificationApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;
    BadgeRepository badgeRepository;

    public ResponseEntity<Object> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);
        Long id = newUserEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<User>> getUsers() {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }
        /*
        Fruit staticFruit = new Fruit();
        staticFruit.setColour("red");
        staticFruit.setKind("banana");
        staticFruit.setSize("medium");
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(staticFruit);
        */
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<Object> createBadge(@ApiParam(value = "", required = true) @Valid @RequestBody Badge badge) {
        BadgeEntity newBadgeEntity = toBadgeEntity(badge);
        badgeRepository.save(newBadgeEntity);
        Long id = newBadgeEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBadgeEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<Badge>> getBadges() {
        List<Badge> badges = new ArrayList<>();
        for (BadgeEntity badgeEntity : badgeRepository.findAll()) {
            badges.add(toBadge(badgeEntity));
        }
        /*
        Fruit staticFruit = new Fruit();
        staticFruit.setColour("red");
        staticFruit.setKind("banana");
        staticFruit.setSize("medium");
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(staticFruit);
        */
        return ResponseEntity.ok(badges);
    }


    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setEmail(user.getEmail());
        entity.setBadges(user.getBadges());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setFirstname(entity.getFirstname());
        user.setLastname(entity.getLastname());
        user.setEmail(entity.getEmail());
        user.badges(entity.getBadges());
        return user;
    }

    private BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        return entity;
    }

    private Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setName(entity.getName());
        return badge;
    }

}
