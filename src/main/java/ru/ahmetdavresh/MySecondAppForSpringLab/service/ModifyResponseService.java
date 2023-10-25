package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.springframework.stereotype.Service;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
