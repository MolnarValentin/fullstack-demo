package com.docker.demo.controller;

import com.docker.demo.domain.Goal;
import com.docker.demo.service.GoalService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {

  private final GoalService goalService;

  public GoalController(GoalService goalService) {
    this.goalService = goalService;
  }

  @GetMapping
  public ResponseEntity<List<Goal>> getAllGoals() {
    List<Goal> goals = goalService.getAllGoals();
    return new ResponseEntity<>(goals, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
    return new ResponseEntity<>(goalService.createGoal(goal), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGoal(@PathVariable String id) {
    goalService.deleteGoal(id);
    return ResponseEntity.noContent().build();
  }
}
