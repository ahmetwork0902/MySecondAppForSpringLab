package ru.ahmetdavresh.MySecondAppForSpringLab.service;

import org.junit.jupiter.api.Test;
import ru.ahmetdavresh.MySecondAppForSpringLab.model.Positions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImplTest {
    @Test
    void calculateQuarterlyBonus() {
        // given
        Positions position = Positions.TL;
        double bonus = 2.0;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus);

        // then
        double expected = 13000.0; // Assuming 4 quarters in a year
        assertThat(result).isEqualTo(expected);
    }
}