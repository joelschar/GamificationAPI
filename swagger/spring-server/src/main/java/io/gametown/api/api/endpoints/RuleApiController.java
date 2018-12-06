package io.gametown.api.api.endpoints;

import io.gametown.api.api.PointScalesApi;
import io.gametown.api.api.RulesApi;
import io.gametown.api.api.model.PointScale;
import io.gametown.api.api.model.Rule;
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
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class RuleApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    @Override
    public ResponseEntity<Rule> createRule(String apiKey, Rule pointScale) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteRule(String apiKey, Rule badge) {
        return null;
    }

    @Override
    public ResponseEntity<List<Rule>> getRules(String apiKey) {
        return null;
    }

    @Override
    public ResponseEntity<Rule> updateRule(String apiKey, Rule badge) {
        return null;
    }

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

}
