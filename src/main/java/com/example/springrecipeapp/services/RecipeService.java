package com.example.springrecipeapp.services;

import com.example.springrecipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
