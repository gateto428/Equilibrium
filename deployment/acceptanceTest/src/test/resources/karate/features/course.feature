Feature: CRUD Course

  Background:
    * url urlPerson
    * url urlCourse
    * def body = read("../data/course.json")
    * def login = { emailPerson: "2jmk3218@gmail.com", pass: "2jmk3218" }
    Given url urlPerson + "/login"
    Given request login
    When method POST
    * def token = $.data.pass

  Scenario: save course successfully
    * set body.creatorCourse = '1049653787'
    Given url urlCourse
    Given header token = token
    Given request body
    When method POST
    Then status 200
    And match $.data.creatorCourse == '1049653787'

  Scenario: save course error no token
    * set body.creatorCourse = '1049653787'
    Given url urlCourse
    Given request body
    When method POST
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: save course error missing parameters per body
    * set body.creatorCourse = null
    Given url urlCourse
    Given request body
    Given header token = token
    When method POST
    Then status 500
    And match $.message == 'Missing parameters per body'
    And match $.code == '003'

  Scenario: save course error user not found
    Given url urlCourse
    Given request body
    Given header token = token
    When method POST
    Then status 500
    And match $.message == 'Error in save course'
    And match $.code == '010'

  Scenario: find course by id successfully
    Given params {'id-course': '2'}
    Given url urlCourse
    Given header token = token
    When method GET
    Then status 200
    And match $.data.idCourse == 2

  Scenario: find course by id error not found
    Given params {'id-course': '0'}
    Given url urlCourse
    Given header token = token
    When method GET
    Then status 500
    And match $.message == 'Course not found'
    And match $.code == '304'

  Scenario: find course by id error missing token
    Given params {'id-course': '0'}
    Given url urlCourse
    When method GET
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: find course by id error missing params
    Given url urlCourse
    Given header token = token
    When method GET
    Then status 500
    And match $.message == 'Missing params'
    And match $.code == '008'


  Scenario: Update course by id successfully
   * set body.idCourse = 2
    Given url urlCourse
    Given request body
    Given header token = token
    When method PUT
    Then status 200
    And match $.data.idCourse == 2

  Scenario: Update course by id error not found
    * set body.idCourse = 0
    Given url urlCourse
    Given request body
    Given header token = token
    When method PUT
    Then status 500
    And match $.message == 'Course not found'
    And match $.code == '304'

  Scenario: Update course by id error missing token
    * set body.idCourse = 2
    Given url urlCourse
    Given request body
    When method PUT
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: update course by id error missing params per body
    Given url urlCourse
    Given request body
    Given header token = token
    When method PUT
    Then status 500
    And match $.message == 'Missing parameters per body'
    And match $.code == '003'

  Scenario: Delete course by id successfully
    Given params {'id-course': '2'}
    Given url urlCourse
    Given header token = token
    When method DELETE
    Then status 200
    And match $.data.idCourse == 2
    And match $.data.isActive == false

  Scenario: delete course by id error not found
    Given params {'id-course': '0'}
    Given url urlCourse
    Given header token = token
    When method DELETE
    Then status 500
    And match $.message == 'Course not found'
    And match $.code == '304'

  Scenario: DELETE course by id error missing token
    Given params {'id-course': '0'}
    Given url urlCourse
    When method DELETE
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: find course by id error missing params
    Given url urlCourse
    Given header token = token
    When method DELETE
    Then status 500
    And match $.message == 'Missing params'
    And match $.code == '008'

  Scenario: Find all courses successfully
    * def urlAll = urlCourse + "/all"
    Given url urlAll
    Given params {'offset': '0', 'limit': '10'}
    Given header token = token
    When method GET
    Then status 200


  Scenario: Find all courses error missing token
    * def urlAll = urlCourse + "/all"
    Given url urlAll
    Given params {'offset': '0', 'limit': '10'}
    When method GET
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: find course by id error missing params
    * def urlAll = urlCourse + "/all"
    Given url urlAll
    Given header token = token
    When method GET
    Then status 500
    And match $.message == 'Missing params'
    And match $.code == '008'