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
import io.gametown.api.repositories.BadgeStatusRepository;
import io.gametown.api.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    BadgeStatusRepository badgeStatusRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ModelUtils tools;

    @Override
    public ResponseEntity<User> getUser(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                        @ApiParam(value = "",required=true ) @PathVariable("userId") Integer userId) {

        UserEntity userEntity = userRepository.findByApplication_ApiKeyAndId(apiKey, userId);

        if(userEntity != null){
            return ResponseEntity.ok(tools.toUser(userEntity));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Badge>> getUserBadges(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                                     @ApiParam(value = "",required=true ) @PathVariable("userId") Integer userId) {

        UserEntity userEntity = userRepository.findByApplication_ApiKeyAndId(apiKey, userId);
        if(userEntity != null) {
            List<BadgeStatusEntity> entities = badgeStatusRepository.findAllByUser(userEntity);
            if (entities != null) {
                List<Badge> badges = new ArrayList<>();
                for (BadgeStatusEntity entity : entities) {
                    badges.add(tools.toBadge(entity.getBadge()));
                }
                return ResponseEntity.ok(badges);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<User>> getUsers(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {

        List<UserEntity> usersEntity = userRepository.findAllByApplication_ApiKeyAndActiveIsTrue(apiKey);

        List<User> users = new ArrayList<>();
        for (UserEntity userEntity: usersEntity ) {
            users.add(tools.toUser(userEntity));
        }

        return ResponseEntity.ok(users);
    }
}
