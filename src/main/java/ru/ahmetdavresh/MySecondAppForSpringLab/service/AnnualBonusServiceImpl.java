package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.springframework.stereotype.Service;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Positions;
import ru.ahmetdavresh.MySecondAppForSpringLab.exception.UnsupportedCodeException;
import java.time.Year;
@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int totalDaysInYear = Year.now().isLeap() ? 366 : 365;
        return salary * bonus * totalDaysInYear * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterlyBonus(Positions position, double salary, double bonus) {
        if (!position.isManager()) {
            throw new UnsupportedCodeException("Quarterly bonus calculation is supported only for managers.");
        }

        int quartersInYear = 4;
        return salary * bonus * position.getPositionCoefficient() / quartersInYear;
    }
}

