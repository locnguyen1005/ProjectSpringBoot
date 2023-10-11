package javahutech.JAVA.services;

import javahutech.JAVA.entity.Category;
import javahutech.JAVA.repository.ICateogoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ICateogoryRepository cateogoryRepository;
    public List<Category> getAllCategories()
    {
        return cateogoryRepository.findAll();
    }
    public  Category getCategoryById (Long id)
    {
        return cateogoryRepository.findById(id).orElse(null);
    }
    public Category saveCategory(Category category)
    {
        return cateogoryRepository.save(category);
    }
    public  void deleteCategory(Long id)
    {
        cateogoryRepository.deleteById(id);
    }
}
