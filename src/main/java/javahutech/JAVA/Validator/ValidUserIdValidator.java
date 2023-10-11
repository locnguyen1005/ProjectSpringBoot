package javahutech.JAVA.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import javahutech.JAVA.Validator.annotation.ValidUserId;
import javahutech.JAVA.entity.User;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user == null)
            return true;
        return user.getId()!=null;
    }
}
