package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.ahmetdavresh.MySecondAppForSpringLab.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
