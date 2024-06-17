package com.recipe.project.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.recipe.project.Application;
import com.recipe.project.datatransfer.*;
import com.recipe.project.enums.ConditionalParameter;
import com.recipe.project.enums.TypeOfDish;
import com.recipe.project.enums.UnitOfMeasure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RecipeControllerApiTest {

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int randomServerPort;

    private WireMockServer wireMockServer;

    private Response response;

//    @BeforeEach
//    void beforeEach() {
//        startWireMockServer();
//    }
//
//    @AfterEach
//    void tearDown() {
//        stopWireMockServer();
//    }
//
//    private void stopWireMockServer() {
//        wireMockServer.stop();
//    }
//
//    private void startWireMockServer() {
//        wireMockServer = new WireMockServer(
//                wireMockConfig().port(8888));
//        wireMockServer.start();
//        configureFor("localhost", wireMockServer.port());
//    }

    @Nested
    @TestInstance(PER_CLASS)
    class DeleteRecipe {
        @Test
        void shouldDeleteRecipe() {
            Long id = 3L;
            String url = "http://localhost:" + randomServerPort + "/v1/recipe-app/" + id;
            response = given().when().delete(url);
            response.then().statusCode(204);
        }
    }

    @Nested
    @TestInstance(PER_CLASS)
    class findAll {
        @Test
        void shouldFetchAllRecipe() throws JsonProcessingException {
            String url = "http://localhost:" + randomServerPort + "/v1/recipe-app/";
            response = given().when().get(url);
            response.then().statusCode(200);
        }
    }

    @Nested
    @TestInstance(PER_CLASS)
    class fetchDetails {
        @Test
        void shouldFetchSpecificRecipe() throws JsonProcessingException {
            RecipeFetchRequestDTO recipeFetchRequestDTO = new RecipeFetchRequestDTO();
            recipeFetchRequestDTO.setServings(4);
            recipeFetchRequestDTO.setTypeOfDish(TypeOfDish.VEGETARIAN);
            recipeFetchRequestDTO.setInstruction("Boil");
            List<IngredientFetchRequestDTO> ingredientFetchRequestDTO = new ArrayList<>();
            IngredientFetchRequestDTO ingredientFetchRequestDTO1 = new IngredientFetchRequestDTO();
            ingredientFetchRequestDTO1.setConditional(ConditionalParameter.EXCLUDE);
            ingredientFetchRequestDTO1.setName("fish");
            IngredientFetchRequestDTO ingredientFetchRequestDTO2 = new IngredientFetchRequestDTO();
            ingredientFetchRequestDTO2.setConditional(ConditionalParameter.INCLUDE);
            ingredientFetchRequestDTO2.setName("onion");
            ingredientFetchRequestDTO.add(ingredientFetchRequestDTO1);
            ingredientFetchRequestDTO.add(ingredientFetchRequestDTO2);
            recipeFetchRequestDTO.setIngredientList(ingredientFetchRequestDTO);

            String requestBody = objectMapper.writeValueAsString(recipeFetchRequestDTO);
            String url = "http://localhost:" + randomServerPort + "/v1/recipe-app/";
            response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .body(requestBody).post(url);
            response.then().statusCode(200);
        }
    }

    @Nested
    @TestInstance(PER_CLASS)
    class modifyDetails {
        @Test
        void shouldModifyDetails() throws JsonProcessingException {
            RecipeUpdateRequestDTO recipeUpdateRequestDTO = new RecipeUpdateRequestDTO();
            recipeUpdateRequestDTO.setName("Updated Dish");
            recipeUpdateRequestDTO.setInstructions("Boil");
            recipeUpdateRequestDTO.setServings(8);
            recipeUpdateRequestDTO.setTypeOfDish(TypeOfDish.VEGETARIAN);
            IngredientUpdateRequestDTO ingredient = new IngredientUpdateRequestDTO();
            ingredient.setId(2L);
            ingredient.setDescription("Onion");
            ingredient.setAmount(BigDecimal.valueOf(1.0));
            ingredient.setUnitOfMeasure(UnitOfMeasure.TEASPOON);
            recipeUpdateRequestDTO.setIngredients(ingredient);
            long id = 3;
            String requestBody = objectMapper.writeValueAsString(recipeUpdateRequestDTO);
            String url = "http://localhost:" + randomServerPort + "/v1/recipe-app/" + id;
            response = given()
                    .contentType(ContentType.JSON)
                    .when().body(requestBody).put(url);
            response.then().statusCode(200);
        }
    }

    @Nested
    @TestInstance(PER_CLASS)
    class modifyDetails1 {
        @Test
        void shouldModifyDetails1() throws JsonProcessingException {
            RecipeUpdateRequestDTO recipeUpdateRequestDTO = new RecipeUpdateRequestDTO();
            recipeUpdateRequestDTO.setName("Updated Dish");
            recipeUpdateRequestDTO.setInstructions("Boil");
            recipeUpdateRequestDTO.setServings(8);
            recipeUpdateRequestDTO.setTypeOfDish(TypeOfDish.VEGETARIAN);
            IngredientUpdateRequestDTO ingredient = new IngredientUpdateRequestDTO();
            ingredient.setId(10L);
            ingredient.setDescription("Onion");
            ingredient.setAmount(BigDecimal.valueOf(1.0));
            ingredient.setUnitOfMeasure(UnitOfMeasure.TEASPOON);
            recipeUpdateRequestDTO.setIngredients(ingredient);
            long id = 3;
            String requestBody = objectMapper.writeValueAsString(recipeUpdateRequestDTO);
            String url = "http://localhost:" + randomServerPort + "/v1/recipe-app/" + id;
            response = given()
                    .contentType(ContentType.JSON)
                    .when().body(requestBody).put(url);
            response.then().statusCode(200);
        }
    }
    @Nested
    @TestInstance(PER_CLASS)
    class insertNewRecipe {
        @Test
        void shouldinsertNewRecipe() throws JsonProcessingException {
            RecipeInsertRequestDTO recipeInsertRequestDTO = new RecipeInsertRequestDTO();
            recipeInsertRequestDTO.setName("New Dish");
            recipeInsertRequestDTO.setInstruction("Boil");
            recipeInsertRequestDTO.setServings(4);
            recipeInsertRequestDTO.setTypeOfDish(TypeOfDish.VEGETARIAN);
            List<IngredientInsertRequestDTO> ingredients = new ArrayList<>();
            IngredientInsertRequestDTO ingredient = new IngredientInsertRequestDTO();
            ingredient.setDescription("Onion");
            ingredient.setUnitOfMeasure(UnitOfMeasure.TEASPOON);
            ingredient.setAmount(BigDecimal.ONE);
            ingredients.add(ingredient);
            recipeInsertRequestDTO.setIngredients(ingredients);
            long id = 3;
            String requestBody = objectMapper.writeValueAsString(recipeInsertRequestDTO);
            String url = "http://localhost:" + randomServerPort + "/v1/recipe-app/insert";
            response = given()
                    .contentType(ContentType.JSON)
                    .when().body(requestBody).post(url);
            response.then().statusCode(201);
        }
    }
}
