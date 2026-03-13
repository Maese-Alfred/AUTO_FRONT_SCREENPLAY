# language: en

Feature: Sending Kudos between collaborators
  As a collaborator in the organization
  I want to send a kudo to a teammate
  So that I can recognize their excellent work

  Scenario: Successfully send a kudo
    Given the collaborator is on the main page
    When they access the Kudos section
    And they select "christopher@sofkianos.com" as sender
    And they select "santiago@sofkianos.com" as recipient
    And they select the category "Teamwork"
    And they write the message "Excelente trabajo en el sprint screenplay"
    And they drag the slider to send the kudo
    Then the kudo with message "Excelente trabajo en el sprint screenplay" appears in the kudos list
