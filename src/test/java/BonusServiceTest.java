import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BonusServiceTest {

    @Test
    void shouldCalculateForRegisteredAndUnderLimit() { // зарегистрированный пользователь НЕ превышает лимит
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1000; // количество
        boolean registered = true;
        long expected = 30; // Ожидаемый бонус

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndOverLimit() { // зарегистрированный пользователь превышает лимит
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1_000_000;
        boolean registered = true;
        long expected = 500;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

// ЗДЕСЬ ИДУТ МОИ ТЕСТЫ ПО ГРАНИЧНЫМ ЗНАЧЕНИЯМ

    // Беру число ПЕРЕД границей

    @org.junit.jupiter.api.Test
    void shouldCalculateForRegisteredAndOverLimitAndbonus499() { // зарегистрированный пользователь превышает лимит
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1_000_000; // Эта переменная означает количество бабосиков
        boolean registered = true;
        long expected = 499;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    // Ожидаемый результат: Когда превышен лимит, то максимальный бонус всегда равен 500
    // Фактический результат: Максимальный бонус равен 500 когда превышен лимит

    // Новый тест. Беру число ЗА границей
    @org.junit.jupiter.api.Test
    void shouldCalculateForRegisteredAndOverLimitAndbonus501() { // зарегистрированный пользователь превышает лимит
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1_000_000;
        boolean registered = true;
        long expected = 501;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }
    // Ожидаемый результат: Когда превышен лимит, то максимальный бонус всегда равен 500
    // Фактический результат: Максимальный бонус равен 500 когда превышен лимит

   /* Проверяем минимальную границу зарегистрированного пользователя
    // Примечание: программа написана так, что проценты начинают появляться когда amount начисляется от 35.
    Это вызвано переменной, которая следует целочисленному делению. Поскольку у нас нет требований в которых чётко прописаны минимальный amount\суммы
    то я взял минимальный допустимый amount. Решение обусловлено работой банков и сотовых операторов, где есть чёткие переводы, от которых работает процент и начисляется бонус


    */
    @org.junit.jupiter.api.Test
   void shouldCalculateForRegisteredAndOverLimitAndbonus100() { // зарегистрированный пользователь перечисляет минимальную сумму
       BonusService service = new BonusService();

       // подготавливаем данные:
       long amount = 100;
       boolean registered = true;
       long expected = 3;

       // вызываем целевой метод:
       long actual = service.calculate(amount, registered);

       // производим проверку (сравниваем ожидаемый и фактический):
       assertEquals(expected, actual);
   }

    // ОР: При начислении минимальной суммы 100 единиц зарегистрированному пользователю начисляется бонус 3 процента
    // ФР: При начислении минимальной суммы 100 единиц зарегистрированному пользователю начисляется бонус 3 процента

    @org.junit.jupiter.api.Test
    void shouldCalculateForRegisteredAndOverLimitAndbonus99() { // зарегистрированный пользователь перечисляет минимальную сумму
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 99;
        boolean registered = true;
        long expected = 0;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    // ОР: При начислении суммы меньше 100 бонус не начисляется
    // ФР: При начислении суммы меньше 100 процент начисляется бонус 2 единицы

    // НЕ зарегистрированный пользователь false

    @Test
    void shouldCalculateForRegisteredAndUnderLimitfalse1000() { // НЕ зарегистрированный пользователь НЕ превышает лимит
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1000; // количество
        boolean registered = false;
        long expected = 10; // Ожидаемый бонус

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    // ОР: Не зарегистрированный пользователь получает 1 процент от суммы. От 1000 единиц он получил 10 бонусов
    // ФР: Не зарегистрированный пользователь получает 1 процент от суммы. От 1000 единиц он получил 10 бонусов

    @Test
    void shouldCalculateForRegisteredAndOverLimitfalse1_000_000() { // НЕ зарегистрированный пользователь превышает лимит
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1_000_000;
        boolean registered = false;
        long expected = 500;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }
    // ОР: Для незарегистрированного пользователя максимальный бонус при превышении лимита остаётся 500 единиц
    // ФР: Для незарегистрированного пользователя максимальный бонус при превышении лимита остаётся 500 единиц
}