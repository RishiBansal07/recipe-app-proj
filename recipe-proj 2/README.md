# Recipe API

This project allows users to manage their favorite recipes. It should allow adding,
updating, removing, and fetching recipes. Additionally, users should be able to filter available recipes
based on one or more of the following criteria:

1. Whether the dish is vegetarian or not
2. The number of servings
3. Specific ingredients (either includes or excludes)
4. Search within the instructions

##########################################################################
### Table Definition
##########################################################################

We have created the below recipe table in reference with Instruction & Ingredient

##Recipe table:

    | ID  | name | servings | typeOfDish | instruction | ingredients|

TypeOfDish field will only accept two values: VEGETARIAN, NONVEGETARIAN
Further, we have other two below referenced tables from Recipe

##Instruction table:

    | ID  | recipeId | recipeInstructions |

##Ingredient table:

    | ID  | description | amount | unitOfMeasure | recipeId

> - Inserted three Recipes into DB using import.sql file (present in resources folder)

###########################################################################
## Design Thought
###########################################################################

```
1. Use of Simple Approach with diiferent layers of Access using Controller and Service layer.
2. There are five different API points.
3. Possible Scenarios Cover in order and Keeping Future Approach of implementation
4. JUnit Followed Mocking of Object verification using Mockito 
5. Integration tests are present in integration folder inside the test directory.
6. To Test Swagger Configured to test and verify Output of the APIs
7. Maven for Build of project and Configuration of Dependencies
```

##########################################################################
## API points
##########################################################################

The project need to expose 4 endpoints, that can talk to database layer.

| OPERATION | ENDPOINT           | DESCRIPTION                                                                                                                          |
|-----------|--------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| GET       | /recipe-app        | Endpoint fetches all the recipes (page, count)                                                                                       |
| POST      | /recipe-app        | Endpoint fetches the required data based on the input variables (servings, typeOfDish, instruction, ingredients (name, conditional)) |
| POST      | /recipe-app/insert | Endpoint inserts the new records based on the data from external provider                                                            |
| PUT       | /recipe-ap/{id}    | Endpoint updates the existing data based on the input data that has to be changed with the unique identifier of Recipe & Ingredients |
| DELETE    | /recipe-ap/{id}    | Endpoint deletes the existing data based on the unique identifier passed from the external provider                                  |             

As per the requirement, while fetching the details about recipe, we have must give a option to include and exclude the specific ingredients.
So the conditional will accept only two values: EXCLUDE, INCLUDE.

##########################################################################
## Skill set used
##########################################################################

- Java 11
- Spring
- H2
- Test Driven Development
- Junit

##########################################################################
## STEPS To Execute
##########################################################################

```
1. Import project in your workspace
2. mvn clean install
3. After Successful build, launch Spring build Application using command 
	mvn spring-boot:run or initiate your application using Intellij for Spring boot
4. once Spring boot initiated, launch Swagger using http://localhost:8082/swagger-ui.html#
5. Execute and perform your API Invocation
7. Cheers and Enjoy API Processing :)
```

## WE ARE READY?

let's start the microservice project, so by default it will start on the port localhost:8082 now!

> Now with the shared POSTMAN collection at the path **./postman/Recipe Collections.postman_collection.json**<br/>
> Import the postman collection on the local postman app on the PC. We are free to try the endpoints one by one.

## Swagger API

> Access Swagger API page on the URL **http://localhost:8082/swagger-ui.html**

###########################################################################
## Enhancements
###########################################################################

> - **Authorization of API**<br />
    > **Required:** Table User, every-request will go through a @PreAuthorize method that will communicate with the user
    table with the UUID coming from the JWT token or specific header value.
> - We can add more fields in Recipe like preparation time, difficulty level of dish, additional notes etc.
> - Tracing of API logs with use of ELK approach
> - Jacoco for code coverage tool - execution can be defined as Min to 80%

> - **Dockerizing the APP**<br />

> - **Using a definitive instance of DB (POSTGRES, ORACLE)**<br />

###########################################################################
## Testing
###########################################################################

```
1. Postman
2. Swagger
3. Junit
```

###########################################################################
## Contact Email
###########################################################################

- brishi2806@gmail.com

###########################################################################
## Thank You
###########################################################################

