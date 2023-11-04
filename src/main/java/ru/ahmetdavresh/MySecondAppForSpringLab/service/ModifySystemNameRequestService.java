package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Request;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Systems;

@Service
public class ModifySystemNameRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request) {
        request.setSystemName(Systems.SN);
        HttpEntity<Request> httpEntity = new HttpEntity<>(request);
        new RestTemplate().exchange("http://localhost:8084/feedback", HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {
        });
    }
}
