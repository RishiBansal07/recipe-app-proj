package com.recipe.project.repository.specification;

import com.recipe.project.datatransfer.RecipeFetchRequestDTO;
import com.recipe.project.entities.Ingredient;
import com.recipe.project.entities.Instruction;
import com.recipe.project.entities.Recipe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.recipe.project.enums.ConditionalParameter.EXCLUDE;
import static com.recipe.project.enums.ConditionalParameter.INCLUDE;

@Component
public class RecipeSpecification {

    public Specification<Recipe> getRecipeConditions(RecipeFetchRequestDTO recipeFetchRequestDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Recipe, Instruction> instructionsJoin = root.join("instruction");
            Subquery<Ingredient> subquery = query.subquery(Ingredient.class);
            Root<Ingredient> tableIngredients = subquery.from(Ingredient.class);
            Subquery<Ingredient> subquery1 = query.subquery(Ingredient.class);
            Root<Ingredient> tableIngredients1 = subquery1.from(Ingredient.class);
            if (!ObjectUtils.isEmpty(recipeFetchRequestDTO.getServings())) {
                predicates.add(criteriaBuilder.equal(root.get("servings"), recipeFetchRequestDTO.getServings()));
            }
            if (!ObjectUtils.isEmpty(recipeFetchRequestDTO.getTypeOfDish())) {
                predicates.add(criteriaBuilder.equal(root.get("typeOfDish"), recipeFetchRequestDTO.getTypeOfDish()));
            }
            if (StringUtils.hasLength(recipeFetchRequestDTO.getInstruction())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(instructionsJoin.get("recipeInstructions")), "%" + recipeFetchRequestDTO.getInstruction().toLowerCase() + "%"));
            }
            if (!CollectionUtils.isEmpty(recipeFetchRequestDTO.getIngredientList())) {
                List<String> includeRequests = recipeFetchRequestDTO.getIngredientList()
                        .stream()
                        .filter(e -> e.getConditional() == INCLUDE)
                        .map(e -> e.getName().toLowerCase())
                        .collect(Collectors.toList());

                List<String> excludeRequests = recipeFetchRequestDTO.getIngredientList()
                        .stream()
                        .filter(e -> e.getConditional() == EXCLUDE)
                        .map(e -> e.getName().toLowerCase())
                        .collect(Collectors.toList());

                if (!CollectionUtils.isEmpty(includeRequests)) {
                    predicates.add(root.get("id").in(subquery.select(tableIngredients.get("recipe")).where(criteriaBuilder.lower(tableIngredients.get("description")).in(includeRequests))));
                }
                if (!CollectionUtils.isEmpty(excludeRequests)) {
                    predicates.add(root.get("id").in(subquery1.select(tableIngredients1.get("recipe")).where(criteriaBuilder.lower(tableIngredients1.get("description")).in(excludeRequests))).not());
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}