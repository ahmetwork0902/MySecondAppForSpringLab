package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.springframework.stereotype.Service;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Positions;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        return salary * bonus * 365 * positions.getPositionCoefficient() / workDays;
    }
}