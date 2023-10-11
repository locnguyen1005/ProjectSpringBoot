package javahutech.JAVA.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import javahutech.JAVA.Validator.annotation.ValidCategoryId;
import javahutech.JAVA.entity.Category;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category>
{
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context)
    {
        return category != null && category.getId() !=null;
    }
}
