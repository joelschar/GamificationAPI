package io.gametown.api.api.endpoints;

import io.gametown.api.api.UsersApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.User;
import io.gametown.api.api.service.ModelUtils;
import io.gametown.api.entities.ApplicationEntity;
import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.BadgeStatusEntity;
import io.gametown.api.entities.UserEntity;
import io.gametown.api.repositories.ApplicationRepository;
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

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ModelUtils tools;

    @Override
    public ResponseEntity<User> getUser(String apiKey, Integer userId) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<UserEntity> usersEntity = applicationEntity.getUsers();

        for (UserEntity userEntity: usersEntity ) {
            if(userEntity.getId() == userId){
                return ResponseEntity.ok(tools.toUser(userEntity));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Badge>> getUserBadges(String apiKey, Integer userId) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<UserEntity> usersEntity = applicationEntity.getUsers();

        List<Badge> badges = new ArrayList<>();

        for (UserEntity userEntity: usersEntity ) {
            if(userEntity.getId() == userId){
                List<BadgeStatusEntity> badgesStatusEntity = userEntity.getBadgesStatus();
                for (BadgeStatusEntity badgeStatusEntity : badgesStatusEntity){
                    badges.add(tools.toBadge(badgeStatusEntity.getBadge()));
                }
                return  ResponseEntity.ok(badges);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<User>> getUsers(String apiKey) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<UserEntity> usersEntity = applicationEntity.getUsers();

        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : usersEntity) {
            users.add(tools.toUser(userEntity));
        }
        return  ResponseEntity.ok(users);
    }
}
