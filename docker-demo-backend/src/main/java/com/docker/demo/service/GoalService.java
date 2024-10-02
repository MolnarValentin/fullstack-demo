package com.docker.demo.service;

import com.docker.demo.domain.Goal;
import com.docker.demo.exception.GoalApiException;
import com.docker.demo.exception.GoalValidationException;
import com.docker.demo.repository.GoalRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoalService {

  private final GoalRepository goalRepository;

  private final Validator validator;

  private final SequenceGeneratorService sequenceGeneratorService;

  public GoalService(GoalRepository goalRepository,
      SequenceGeneratorService sequenceGeneratorService) {
    this.goalRepository = goalRepository;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
    this.sequenceGeneratorService = sequenceGeneratorService;
  }

  public List<Goal> getAllGoals() {
    log.info("TRYING TO FETCH GOALS");
    try {
      List<Goal> goals = goalRepository.findAll();
      log.info("FETCHED GOALS");
      return goals;
    } catch (Exception e) {
      log.error("ERROR FETCHING GOALS", e);
      throw new GoalApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Goal createGoal(Goal goal) {
    log.info("TRYING TO STORE GOALS");

    Set<ConstraintViolation<Goal>> violations = validator.validate(
        goal);
    if (!violations.isEmpty()) {
      throw new GoalValidationException(
          "Goal is invalid: " + violations);
    }

    try {
      Goal savedGoal = goalRepository.save(
          new Goal(String.valueOf(sequenceGeneratorService.generateSequence(Goal.SEQUENCE_NAME)),
              goal.getText()));
      log.info("STORED NEW GOAL");
      return savedGoal;
    } catch (Exception e) {
      log.error("ERROR STORING GOAL", e);
      throw new GoalApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public void deleteGoal(
      String id) {
    log.info("TRYING TO DELETE GOALS");
    try {
      goalRepository.deleteById(id);
      log.info("DELETED GOAL");
    } catch (Exception e) {
      log.error("ERROR DELETING GOAL", e);
      throw new GoalApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
