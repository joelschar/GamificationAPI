package io.gametown.api.api.endpoints;

import com.sun.security.ntlm.Server;
import io.gametown.api.api.PointScalesApi;
import io.gametown.api.api.RulesApi;
import io.gametown.api.api.model.PointScale;
import io.gametown.api.api.model.Rule;
import io.gametown.api.entities.ApplicationEntity;
import io.gametown.api.entities.PointScaleEntity;
import io.gametown.api.entities.RuleEntity;
import io.gametown.api.repositories.ApplicationRepository;
import io.gametown.api.repositories.PointScaleRepository;
import io.gametown.api.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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


    private RuleEntity toRuleEntity(Rule rule) {
        RuleEntity entity = new RuleEntity();
        entity.setKondition(rule.getCondition());
        entity.setValue(rule.getValue());
        return entity;
    }

    private Rule toRule(RuleEntity entity) {
        Rule rule = new Rule();
        rule.setCondition(entity.getKondition());
        rule.setValue(entity.getValue());
        return rule;
    }

    @Override
    public ResponseEntity<Rule> createRule(String apiKey, Rule rule) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        // Create Rule
        RuleEntity newRuleEntity = toRuleEntity(rule);
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
    public ResponseEntity<Void> deleteRule(String apiKey, Rule rule) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        RuleEntity ruleForUpdate = toRuleEntity(rule);
        String valueRules = ruleForUpdate.getValue();

            for (RuleEntity ruleEntity : rules) {
                if(valueRules.equals(ruleEntity.getValue())) {
                    ruleEntity.setIsActive(false);
                }
            }

      // TODO Check other possibilities to make it good maybe preferable to change return type
        return (ResponseEntity<Void>) ResponseEntity.accepted();
    }

    @Override
    public ResponseEntity<List<Rule>> getRules(String apiKey) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        List<Rule> ruleList = new ArrayList<>();

        for (RuleEntity ruleEntity : rules) {
            if (ruleEntity.getIsActive()) {
                ruleList.add(toRule(ruleEntity));
            }
        }

        return  ResponseEntity.ok(ruleList);
    }

    @Override
    public ResponseEntity<Rule> updateRule(String apiKey, Rule rule) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        RuleEntity ruleForUpdate = toRuleEntity(rule);
        String valueRules = ruleForUpdate.getValue();

        for (RuleEntity ruleEntity : rules) {
            if(valueRules.equals(ruleEntity.getValue())) {
              ruleEntity.setKondition(ruleForUpdate.getKondition());
              ruleEntity.setIsActive(true);
              return ResponseEntity.ok(toRule(ruleEntity));
            }
        }
        // Todo check how to setup no rules or don't know
        // return ResponseEntity.notFound(rule);
        return null;
    }
}
