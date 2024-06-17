package com.recipe.project.controller;

import com.recipe.project.datatransfer.RecipeFetchRequestDTO;
import com.recipe.project.datatransfer.RecipeInsertRequestDTO;
import com.recipe.project.datatransfer.RecipeUpdateRequestDTO;
import com.recipe.project.entities.Recipe;
import com.recipe.project.exception.ErrorResponse;
import com.recipe.project.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/recipe-app")
@AllArgsConstructor
@Tag(name = "Recipe-Controller", description = "(Version 1) Controller that basically does the CRUD operations related to the Recipe!")
public class RecipeController {

    private final RecipeService recipeService;

    @Operation(description = "Get details related to the recipe app", operationId = "fetchDetails")
    //@Operation(description = "Get details related to the recipe app")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation")})
    @PostMapping(value = "", produces = {"application/json", "application/xml"})
    public List<Recipe> fetchDetails(@RequestBody RecipeFetchRequestDTO recipeFetchRequestDTO) {
        return recipeService.findRecipes(recipeFetchRequestDTO);
    }

    //@Operation(description = "Insert new details into the recipe app DB", operationId = "insertDetails")
    @Operation(description = "Insert new details into the recipe app DB")

    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @PostMapping(value = "/insert", produces = {"application/json", "application/xml"})
    public ResponseEntity<Long> insertDetails(@Valid @RequestBody RecipeInsertRequestDTO recipeInsertRequestDTO) {
        Long id = recipeService.saveRecipe(recipeInsertRequestDTO);
        return ResponseEntity.created(URI.create("/recipe-app/" + id)).body(id);
    }

    //@Operation(description = "Modify existing details of Recipe", operationId = "modifyDetails")
    @Operation(description = "Modify existing details of Recipe")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "ID not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @PutMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Long> modifyDetails(@PathVariable("id") Long id, @Valid @RequestBody RecipeUpdateRequestDTO recipeInsertRequestDTO) {
        Long responseId = recipeService.updateRecipe(id, recipeInsertRequestDTO);
        return ResponseEntity.ok(responseId);
    }

    @Operation(description = "Delete recipe from DB", operationId = "deleteDetails")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
        @DeleteMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Void> deleteDetails(@PathVariable("id") Long id) {
        recipeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@Operation(description = "Fetch all existing Recipe from the DB", operationId = "deleteDetails")
    @Operation(description = "Fetch all existing Recipe from the DB")

    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @GetMapping
    public ResponseEntity<List<Recipe>> findAll(@RequestParam(value = "count", defaultValue = "20", required = false) Integer count,
                                                @RequestParam(value = "page", defaultValue = "1", required = false) Integer page
    ) {

        return ResponseEntity.ok(recipeService.findAll(page, count));
    }
}