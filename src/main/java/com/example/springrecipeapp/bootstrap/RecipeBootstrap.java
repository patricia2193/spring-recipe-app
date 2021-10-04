package com.example.springrecipeapp.bootstrap;

import com.example.springrecipeapp.constants.Difficulty;
import com.example.springrecipeapp.model.*;
import com.example.springrecipeapp.repositories.CategoryRepository;
import com.example.springrecipeapp.repositories.RecipeRepository;
import com.example.springrecipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap Data");
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

            Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cups");

        if(!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        //get categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category not found!");
        }

        Optional<Category> europeanCategoryOptional = categoryRepository.findByDescription("European");

        if(!europeanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category not found!");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category europeanCategory = europeanCategoryOptional.get();

        //Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Delicious Guacamole");
        guacRecipe.setPrepTime(20);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Insert directions here!");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Add recipe notes here");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("salt", new BigDecimal("0.5"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("lime/lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("chillies", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("tomato", new BigDecimal(1), eachUom));

        guacRecipe.getCategories().add(americanCategory);

        //add to return list
        recipes.add(guacRecipe);

        return recipes;
    }
}
