Feature: CRUD Person

  Background:
    * url urlPerson
    * def body = read("../data/person.json")
    * def login = { emailPerson: "2jmk3218@gmail.com", pass: "2jmk3218" }
    Given url urlPerson + "/login"
    Given request login
    When method POST
    * def token = $.data.pass


  Scenario: save person successfully
    * set body.idPerson = '0000001'
    * set body.emailPerson = 'nicolas.suarez@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = 'true'
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Nicolas'
    * set body.lastNamePerson = 'suarez'
    Given url urlPerson
    Given header token = token
    Given request body
    When method POST
    Then status 200
    And match $.data.idPerson == '0000001'
    And match $.data.emailPerson == 'nicolas.suarez@uptc.edu.co'

  Scenario: save person error no token
    * set body.idPerson = '0000002'
    * set body.emailPerson = 'nicolas2.suarez@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = 'true'
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Nicolas'
    * set body.lastNamePerson = 'suarez'
    Given url urlPerson
    Given request body
    When method POST
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: save person error missing parameters per body
    * set body.idPerson = '0000002'
    * set body.emailPerson = 'nicolas2.suarez@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = null
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Nicolas'
    * set body.lastNamePerson = 'suarez'
    Given url urlPerson
    Given request body
    Given header token = token
    When method POST
    Then status 500
    And match $.message == 'Missing parameters per body'
    And match $.code == '003'

  Scenario: login person successfully
    * def urlLogin = urlPerson + "/login"
    * set body.emailPerson = '2jmk3218@gmail.com'
    * set body.pass = '2jmk3218'
    * set body.rolType = 'ADMINISTRATOR'
    * set body.isActive = 'true'
    * set body.namePerson = 'Nicolas'
    * set body.lastNamePerson = 'suarez'
    Given url urlLogin
    Given request body
    When method POST
    Then status 200
    And match $.data.emailPerson == '2jmk3218@gmail.com'

  Scenario: login person invalid credentials
    * def urlLogin = urlPerson + "/login"
    * set body.emailPerson = '2jmk3218@gmail.com'
    * set body.pass = 'bad key'
    * set body.rolType = 'ADMINISTRATOR'
    * set body.isActive = 'true'
    * set body.namePerson = 'Nicolas'
    * set body.lastNamePerson = 'suarez'
    Given url urlLogin
    Given request body
    When method POST
    Then status 500
    And match $.message == 'Invalid credentials, please review your credentials'
    And match $.code == '302'


  Scenario: Register person successfully
    * def urlRegister = urlPerson + "/singin"
    * set body.idPerson = '0000002'
    * set body.emailPerson = 'juan.acero04@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = 'false'
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Juan'
    * set body.lastNamePerson = 'Acero'
    Given url urlRegister
    Given request body
    When method POST
    Then status 200
    And match $.data.idPerson == '0000002'
    And match $.data.emailPerson == 'juan.acero04@uptc.edu.co'

  Scenario: Register person error missing parameters per body
    * def urlRegister = urlPerson + "/singin"
    * set body.idPerson = '0000002'
    * set body.emailPerson = 'juan.acero04@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = null
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Juan'
    * set body.lastNamePerson = 'Acero'
    Given url urlRegister
    Given request body
    When method POST
    Then status 500
    And match $.message == 'Missing parameters per body'
    And match $.code == '003'

  Scenario: Register person error save person
    * def urlRegister = urlPerson + "/singin"
    * set body.idPerson = '0000002'
    * set body.emailPerson = 'juan.acero04@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = 'false'
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Juan'
    * set body.lastNamePerson = 'Acero'
    Given url urlRegister
    Given request body
    When method POST
    Then status 500
    And match $.message == 'Error in save person'
    And match $.code == '005'

    Scenario: find person by id successfully
      * def urlFindId = urlPerson + "/profile"
      Given params {'id-person': '1049653787'}
      Given url urlFindId
      Given header token = token
      When method GET
      Then status 200
      And match $.data.idPerson == '1049653787'

  Scenario: find person by id error missing params
    * def urlFindId = urlPerson + "/profile"
    Given url urlFindId
    Given header token = token
    When method GET
    Then status 500
    And match $.message == 'Missing params'
    And match $.code == '008'

  Scenario: find person by id error missing per header (Token)
    * def urlFindId = urlPerson + "/profile"
    Given url urlFindId
    Given params {'id-person': '1049653787'}
    When method GET
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: delete person by id successfully
    Given params {'id-person': '0000001'}
    Given url urlPerson
    Given header token = token
    When method DELETE
    Then status 200

  Scenario: delete person by id  error missing params
    Given url urlPerson
    Given header token = token
    When method DELETE
    Then status 500
    And match $.message == 'Missing params'
    And match $.code == '008'

  Scenario: Delete person by id error missing per header (Token)
    Given params {'id-person': '0000001'}
    Given url urlPerson
    When method DELETE
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: Activate person by id successfully
    Given params {'id-person': '0000001'}
    Given url urlPerson
    Given header token = token
    When method PUT
    Then status 200

  Scenario: Activate person by id  error missing params
    Given url urlPerson
    Given header token = token
    When method PUT
    Then status 500
    And match $.message == 'Missing params'
    And match $.code == '008'

  Scenario: Activate person by id error missing per header (Token)
    Given params {'id-person': '0000001'}
    Given url urlPerson
    When method PUT
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: Update person successfully
    * def urlUpdate = urlPerson + "/profile"
    * set body.idPerson = '0000001'
    * set body.emailPerson = 'nicolas.suarez@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = 'true'
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Alejandro'
    * set body.lastNamePerson = 'Alvarez'
    Given url urlUpdate
    Given header token = token
    Given request body
    When method PUT
    Then status 200

  Scenario: Update person error no token
    * def urlUpdate = urlPerson + "/profile"
    * set body.idPerson = '0000002'
    * set body.emailPerson = 'nicolas2.suarez@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = 'true'
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Nicolas'
    * set body.lastNamePerson = 'suarez'
    Given url urlUpdate
    Given request body
    When method PUT
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'

  Scenario: Update person error missing parameters per body
    * def urlUpdate = urlPerson + "/profile"
    * set body.idPerson = '0000001'
    * set body.emailPerson = 'nicolas.suarez@uptc.edu.co'
    * set body.pass = 'abc123'
    * set body.isActive = null
    * set body.rolType = 'ADMINISTRATOR'
    * set body.namePerson = 'Alejandro'
    * set body.lastNamePerson = 'Alvarez'
    Given url urlUpdate
    Given request body
    Given header token = token
    When method PUT
    Then status 500
    And match $.message == 'Missing parameters per body'
    And match $.code == '003'

  Scenario: Get all  person  successfully
    * def urlAll = urlPerson + "/all"
    Given params {'offset': '2', 'limit': '10'}
    Given url urlAll
    Given header token = token
    When method GET
    Then status 200

  Scenario: Get all  person error missing params
    * def urlAll = urlPerson + "/all"
    Given url urlAll
    Given header token = token
    When method GET
    Then status 500
    And match $.message == 'Missing params'
    And match $.code == '008'

  Scenario: Get all  person missing per header (Token)
    * def urlAll = urlPerson + "/all"
    Given url urlAll
    Given params {'offset': '2', 'limit': '10'}
    When method GET
    Then status 500
    And match $.message == 'Missing parameters per header'
    And match $.code == '004'