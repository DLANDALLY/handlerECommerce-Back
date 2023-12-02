package fr.dlyprod.ecommerce.services.utils;

import fr.dlyprod.ecommerce.entities.Categorie;
import fr.dlyprod.ecommerce.forms.CategoryForm;
import org.modelmapper.ModelMapper;

public class CategoryUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Categorie convertToCategory(CategoryForm categoryForm) {
        return modelMapper.map(categoryForm, Categorie.class);
    }

}
