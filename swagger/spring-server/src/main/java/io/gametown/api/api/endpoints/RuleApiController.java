package io.gametown.api.api.endpoints;

import io.gametown.api.api.RulesApi;
import io.gametown.api.api.model.Rule;
import io.gametown.api.entities.ApplicationEntity;
import io.gametown.api.entities.RuleEntity;
import io.gametown.api.repositories.ApplicationRepository;
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

        if(applicationEntity.getRules().contains(toRuleEntity(rule))) {
            RuleEntity ruleToDelete = ruleRepository.findById((long)rule.getId()).orElseThrow(() -> new RuntimeException());
            ruleToDelete.setActive(false);
            ruleRepository.save(ruleToDelete);
        }
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<List<Rule>> getRules(String apiKey) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        List<Rule> ruleList = new ArrayList<>();

        for (RuleEntity ruleEntity : rules) {
            if (ruleEntity.isActive()) {
                ruleList.add(toRule(ruleEntity));
            }
        }

        return  ResponseEntity.ok(ruleList);
    }

    @Override
    public ResponseEntity<Rule> updateRule(String apiKey, Rule rule) {
        ApplicationEntity applicationEntity = applicationRepository.findById(apiKey).orElseThrow(() -> new RuntimeException());
        List<RuleEntity> rules = applicationEntity.getRules();

        RuleEntity ruleTemp = toRuleEntity(rule);

        if(rules.contains(ruleTemp)) {
            RuleEntity ruleToUpdate = ruleRepository.findById((long)rule.getId()).orElseThrow(() -> new RuntimeException());
            ruleToUpdate.setValue(ruleTemp.getValue());
            ruleToUpdate.setKondition(ruleTemp.getKondition());
            ruleToUpdate.setActive(true);
            ruleRepository.save(ruleToUpdate);
        }

        return ResponseEntity.status(204).build();
    }
}
