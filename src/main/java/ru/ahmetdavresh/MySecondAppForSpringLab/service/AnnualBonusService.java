package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.springframework.stereotype.Service;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
    double calculateQuarterlyBonus(Positions positions, double salary, double bonus);
}