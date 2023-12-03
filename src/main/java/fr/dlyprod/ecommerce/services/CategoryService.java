package fr.dlyprod.ecommerce.services;

import fr.dlyprod.ecommerce.entities.Categorie;
import fr.dlyprod.ecommerce.exceptions.CategoryException;
import fr.dlyprod.ecommerce.forms.CategoryForm;
import fr.dlyprod.ecommerce.repositories.CategoryRepository;
import fr.dlyprod.ecommerce.services.utils.CategoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categorie> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Categorie createCategory(CategoryForm categoryForm) throws IOException {
        CategoryUtils.checkPhoto(categoryForm.getPhoto());

        return categoryRepository.save(CategoryUtils.convertToCategory(categoryForm));
    }

    public Categorie updateCategory(Long id, CategoryForm categoryForm) throws RuntimeException {
        if (!categoryRepository.existsById(id))
            throw new CategoryException(id);

        Categorie categorie = getCategoryById(id);
        if(categorie == null)
            throw new CategoryException("La categorie n'a pas été trouvé");

        return categoryRepository.save(CategoryUtils.convertToCategory(categoryForm));
    }

    public boolean deleteCategory(Long id) {
        if (!categoryRepository.existsById(id))
            return false;

        categoryRepository.deleteById(id);
        return true;
    }

    public Categorie getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

}
