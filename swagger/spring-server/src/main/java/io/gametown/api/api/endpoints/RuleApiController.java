package io.gametown.api.api.endpoints;

import io.gametown.api.api.RulesApi;
import io.gametown.api.api.model.Badge;
import io.gametown.api.api.model.PointScale;
import io.gametown.api.api.model.Rule;
import io.gametown.api.api.service.ModelUtils;
import io.gametown.api.entities.ApplicationEntity;
import io.gametown.api.entities.BadgeEntity;
import io.gametown.api.entities.PointScaleEntity;
import io.gametown.api.entities.RuleEntity;
import io.gametown.api.repositories.ApplicationRepository;
import io.gametown.api.repositories.BadgeRepository;
import io.gametown.api.repositories.PointScaleRepository;
import io.gametown.api.repositories.RuleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class RuleApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    ModelUtils tools;

    @Override
    public ResponseEntity<Rule> createRule(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                           @ApiParam(value = "" ,required=true ) @RequestBody Rule rule) {
        rule.setActive(true);
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());

        List<RuleEntity> rules = applicationEntity.getRules();

        int badgeID = -1;
        int pointScaleID = -1;

        if(rule.getBadge() != null)
            badgeID = rule.getBadge().getId();

        if(rule.getPointScale() != null)
            pointScaleID = rule.getPointScale().getId();

        PointScaleEntity pointScaleEntity;
        if(pointScaleID > 0) {
            pointScaleEntity = pointScaleRepository.findById(pointScaleID).orElseThrow(() -> new RuntimeException());
            rule.setPointScale(tools.toPointScale(pointScaleEntity));
        }
        else {
            rule.setPointScale(null);
        }

        BadgeEntity badgeEntity;
        if(badgeID > 0){
            badgeEntity = badgeRepository.findById(badgeID).orElseThrow(() -> new RuntimeException());
            rule.setBadge(tools.toBadge(badgeEntity));
        }
        else {
            rule.setBadge(null);
        }

        // Create the PointScale
        RuleEntity newRuleEntity = tools.toRuleEntity(rule);
        if(rule.getBadge()!=null) {
            newRuleEntity.getBadgeEntity().setId(rule.getBadge().getId());
        }
        if(rule.getPointScale()!=null){
            newRuleEntity.getPointScaleEntity().setId(rule.getPointScale().getId());
        }

        ruleRepository.save(newRuleEntity);
        Long id = newRuleEntity.getId();

        rules.add(newRuleEntity);
        applicationEntity.setRules(rules);
        applicationRepository.save(applicationEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newRuleEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteRule(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                           @ApiParam(value = ""  ) @RequestBody Rule rule) {

        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rulesEntity = applicationEntity.getRules();

        for (RuleEntity ruleEntity: rulesEntity ) {
            if(ruleEntity.getId() == rule.getId()){
                ruleEntity.setActive(false);
                ruleRepository.save(ruleEntity);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }

        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<List<Rule>> getRules(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        List<Rule> ruleList = new ArrayList<>();

        for (RuleEntity ruleEntity : rules) {
            if (ruleEntity.isActive()) {
                ruleList.add(tools.toRule(ruleEntity));
            }
        }

        return  ResponseEntity.ok(ruleList);
    }

    @Override
    public ResponseEntity<Rule> updateRule(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                           @ApiParam(value = "" ,required=true ) @RequestBody Rule rule) {

        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rulesEntity = applicationEntity.getRules();

        int badgeID = rule.getBadge().getId();
        int pointScaleID = rule.getPointScale().getId();

        BadgeEntity badgeEntity = badgeRepository.findById(badgeID).orElseThrow(() -> new RuntimeException());
        PointScaleEntity pointScaleEntity = pointScaleRepository.findById(pointScaleID).orElseThrow(() -> new RuntimeException());

        for (RuleEntity ruleEntity: rulesEntity ) {
            if(ruleEntity.getId() == rule.getId()){
                RuleEntity ruleToUpdate = ruleEntity;
                ruleToUpdate.setActive(rule.getActive());

                ruleToUpdate.setPointScaleEntity((pointScaleEntity));
                ruleToUpdate.setBadgeEntity(badgeEntity);
                ruleToUpdate.setNbrPoint(rule.getNbrPoints());
                ruleToUpdate.setValue(rule.getValue());
                ruleRepository.save(ruleToUpdate);
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
