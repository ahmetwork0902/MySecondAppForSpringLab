package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.springframework.stereotype.Service;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Request;

@Service
public class ModifySourceRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request) {
        request.setSource("Изменение source");
    }
}
