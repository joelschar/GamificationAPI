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
public class UserApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BadgeRepository badgeRepository;

    /*
    @Override
    public ResponseEntity<Object> createBadge(Integer userId, Badge badge) {
        // Adding a Badge to a the User
        UserEntity myUserEntity = userRepository.findOne(Long.valueOf(userId));
        myUserEntity.addBadgesItem(toBadgeEntity(badge));
        userRepository.save(myUserEntity);

        // Create the Badge
        BadgeEntity newBadgeEntity = toBadgeEntity(badge);
        badgeRepository.save(newBadgeEntity);
        Long id = newBadgeEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBadgeEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Object> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);
        Long id = newUserEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<User> getUser(String apiKey, Integer userId) {
        //UserEntity myUserEntity = userRepository.findOne(Long.valueOf(userId));
       // User myUser = toUser(myUserEntity);
        //return ResponseEntity.ok(myUser);
        return null;
    }

    @Override
    public ResponseEntity<List<Badge>> getUserBadges(String apiKey, Integer userId) {
        List<Badge> badges = new ArrayList<>();
        //UserEntity myUserEntity = userRepository.findOne(Long.valueOf(userId));
        //for (BadgeEntity myBadgeEntity : myUserEntity.getBadges()) {
        //    badges.add(toBadge(myBadgeEntity));
        //}
        //return ResponseEntity.ok(badges);
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getUsers(String apiKey) {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }
    */

    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setEmail(user.getEmail());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setFirstname(entity.getFirstname());
        user.setLastname(entity.getLastname());
        user.setEmail(entity.getEmail());
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

    @Override
    public ResponseEntity<User> getUser(String apiKey, Integer userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Badge>> getUserBadges(String apiKey, Integer userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getUsers(String apiKey) {
        return null;
    }
}
