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
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<PointScaleEntity> pointScales = applicationEntity.getPointScales();

        // Create the PointScale
        PointScaleEntity newPointScaleEntitiy = tools.toPointScaleEntity(pointScale);
        pointScaleRepository.save(newPointScaleEntitiy);
        Long id = newPointScaleEntitiy.getId();

        pointScales.add(newPointScaleEntitiy);
        applicationEntity.setPointScales(pointScales);
        applicationRepository.save(applicationEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPointScaleEntitiy.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deletePointScale(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                                 @ApiParam(value = ""  ) @RequestBody PointScale pointScale) {

        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());

        if(applicationEntity.getBadges().contains(tools.toPointScaleEntity(pointScale))){

            PointScaleEntity PointScaleEntitiyToDelete = pointScaleRepository.findById(pointScale.getId()).orElseThrow(() -> new RuntimeException());
            PointScaleEntitiyToDelete.setActive(false);
        }
        return ResponseEntity.status(204).build();

    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<PointScaleEntity> pointScales = applicationEntity.getPointScales();

        List<PointScale> pointScaleList = new ArrayList<>();
        for(PointScaleEntity pointScaleEntity : pointScales){
            if(pointScaleEntity.isActive())
                pointScaleList.add(tools.toPointScale(pointScaleEntity));
        }

        return ResponseEntity.ok(pointScaleList);
    }

    @Override
    public ResponseEntity<PointScale> updatePointScale(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                                       @ApiParam(value = "" ,required=true ) @RequestBody PointScale pointScale) {

        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<PointScaleEntity> pointScaleEntity = applicationEntity.getPointScales();
        int indexOfBadge = pointScaleEntity.indexOf(tools.toPointScaleEntity(pointScale));
        pointScaleEntity.get(indexOfBadge).setName(pointScale.getName());
        applicationEntity.setPointScales(pointScaleEntity);
        applicationRepository.save(applicationEntity);

        return ResponseEntity.status(204).build();
    }
}
