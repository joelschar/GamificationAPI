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
import io.gametown.api.repositories.RuleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    ModelUtils tools;

    @Override
    public ResponseEntity<Rule> createRule(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                           @ApiParam(value = "" ,required=true ) @RequestBody Rule pointScale) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteRule(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey,
                                           @ApiParam(value = ""  ) @RequestBody Rule badge) {
        //TODO Corriger YAML Rule badge
        /*
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        if(applicationEntity.getRules().contains(tools.toRuleEntity(rule))) {
            RuleEntity ruleToDelete = ruleRepository.findById((long)rule.getId()).orElseThrow(() -> new RuntimeException());
            ruleToDelete.setActive(false);
            ruleRepository.save(ruleToDelete);
        }
        */
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
                                           @ApiParam(value = "" ,required=true ) @RequestBody Rule badge) {
        //TODO : COrriger YAML Rule badge
        /*
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        RuleEntity ruleTemp = tools.toRuleEntity(rule);

        if(rules.contains(ruleTemp)) {
            RuleEntity ruleToUpdate = ruleRepository.findById((long)rule.getId()).orElseThrow(() -> new RuntimeException());
            ruleToUpdate.setValue(ruleTemp.getValue());
            ruleToUpdate.setActive(true);
            ruleRepository.save(ruleToUpdate);
        }
        */
        return ResponseEntity.status(204).build();
    }
}
