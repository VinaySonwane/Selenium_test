Feature: Data Driven Login

Scenario: Login test user1
  Given user enters "user1"
  Then login should be "success"

Scenario: Login test user2
  Given user enters "user2"
  Then login should be "success"

Scenario: Login test user3
  Given user enters "user3"
  Then login should be "success"

Scenario: Login test user4
  Given user enters "user4"
  Then login should be "fail"

Scenario: Login test user5
  Given user enters "user5"
  Then login should be "success"

Scenario: Login test user6
  Given user enters "user6"
  Then login should be "success"

Scenario: Login test user7
  Given user enters "user7"
  Then login should be "success"

Scenario: Login test user8
  Given user enters "user8"
  Then login should be "fail"

Scenario: Login test user9
  Given user enters "user9"
  Then login should be "success"

Scenario: Login test user10
  Given user enters "user10"
  Then login should be "success"

