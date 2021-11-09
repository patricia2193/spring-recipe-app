package com.example.springrecipeapp.converters;

import com.example.springrecipeapp.commands.CategoryCommand;
import com.example.springrecipeapp.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
