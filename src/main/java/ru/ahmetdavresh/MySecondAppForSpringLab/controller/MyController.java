package ru.ahmetdavresh.MySecondAppForSpringLab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ahmetdavresh.MySecondAppForSpringLab.exception.ValidationFailedException;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Codes;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.ErrorCodes;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.ErrorMessages;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Request;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Response;
import ru.ahmetdavresh.MySecondAppForSpringLab.service.ModifyRequestService;
import ru.ahmetdavresh.MySecondAppForSpringLab.service.ModifyResponseService;
import ru.ahmetdavresh.MySecondAppForSpringLab.service.ValidationService;
import ru.ahmetdavresh.MySecondAppForSpringLab.util.DateTimeUtil;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(
            ValidationService validationService,
            @Qualifier("modifySystemTimeResponseService") ModifyResponseService modifyResponseService,
            @Qualifier("modifySystemNameRequestService") ModifyRequestService modifyRequestService
    ) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("Получен запрос обратной связи: {}", request);

        long startTime = System.currentTimeMillis();

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            handleValidationException(e, response);
        } catch (Exception e) {
            handleUnknownException(e, response);
        }

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("Время выполнения в миллисекундах: {}", executionTime);
        log.info("Ответ: {}", response);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }

    private void handleValidationException(ValidationFailedException e, Response response) {
        log.error("Ошибка валидации: {}", e.getMessage());
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
        response.setErrorMessage(ErrorMessages.VALIDATION);
    }

    private void handleUnknownException(Exception e, Response response) {
        log.error("Произошла неизвестная ошибка: {}", e.getMessage());
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
        response.setErrorMessage(ErrorMessages.UNKNOWN);
    }
}