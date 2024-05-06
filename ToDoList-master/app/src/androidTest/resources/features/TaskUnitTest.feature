Feature: Does the task work?
  Testing of task

  Scenario: Task share info
    Given I have a task
    When I create a task from this task
    Then The tasks should have the same data