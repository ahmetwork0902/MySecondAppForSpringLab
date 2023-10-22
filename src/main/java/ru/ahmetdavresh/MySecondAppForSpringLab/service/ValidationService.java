package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.springframework.validation.BindingResult;
import ru.ahmetdavresh.MySecondAppForSpringLab.exception.ValidationFailedException;

import javax.naming.Binding;

public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
