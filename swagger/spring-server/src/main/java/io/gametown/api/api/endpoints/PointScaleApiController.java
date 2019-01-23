package io.gametown.api.api.endpoints;

import io.gametown.api.api.PointScalesApi;
import io.gametown.api.api.UsersApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.PointScale;
import io.gametown.api.api.model.User;
import io.gametown.api.api.service.ModelUtils;
import io.gametown.api.entities.ApplicationEntity;
import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.PointScaleEntity;
import io.gametown.api.entities.UserEntity;
import io.gametown.api.repositories.ApplicationRepository;
import io.gametown.api.repositories.BadgeRepository;
import io.gametown.api.repositories.PointScaleRepository;
import io.gametown.api.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class PointScaleApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ModelUtils tools;


    @Override
    public ResponseEntity<PointScale> createPointScale(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                                       @ApiParam(value = "" ,required=true ) @RequestBody PointScale pointScale) {
        pointScale.setActive(true);

        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());

        // Create the PointScale
        PointScaleEntity newPointScaleEntity = tools.toPointScaleEntity(pointScale);
        newPointScaleEntity.setApplication(applicationEntity);
        pointScaleRepository.save(newPointScaleEntity);

        int id = newPointScaleEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPointScaleEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deletePointScale(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                                 @ApiParam(value = ""  ) @RequestBody PointScale pointScale) {
        PointScaleEntity pointScaleEntityToDelete = pointScaleRepository.findByApplication_ApiKeyAndId(apiKey, pointScale.getId());
        if(pointScaleEntityToDelete == null)
            return ResponseEntity.status(204).build();
        pointScaleEntityToDelete.setActive(false);
        pointScaleRepository.save(pointScaleEntityToDelete);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {

        List<PointScaleEntity> pointScaleEntitys = pointScaleRepository.findAllByApplication_ApiKeyAndActiveIsTrue(apiKey);

        List<PointScale> pointScales = new ArrayList<>();
        for (PointScaleEntity pointScaleEntity: pointScaleEntitys ) {
            pointScales.add(tools.toPointScale(pointScaleEntity));
        }

        return ResponseEntity.ok(pointScales);
    }

    @Override
    public ResponseEntity<PointScale> updatePointScale(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                                       @ApiParam(value = "" ,required=true ) @RequestBody PointScale pointScale) {

        PointScaleEntity pointScaleEntityToUpdate = pointScaleRepository.findByApplication_ApiKeyAndId(apiKey, pointScale.getId());
        if(pointScaleEntityToUpdate == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        pointScaleEntityToUpdate.setName(pointScale.getName());
        pointScaleEntityToUpdate.setActive(pointScale.getActive());
        pointScaleRepository.save(pointScaleEntityToUpdate);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }
}
