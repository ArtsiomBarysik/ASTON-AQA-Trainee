package homework.junit;
import org.example.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HomeWork14Tests {
    @Test
    @DisplayName("Проверка функции c нулем")
    public void factorialWithZero() {
        assertTrue(Main.getFactorial(0) == 1);
    }
    @Test
    @DisplayName("Проверка функции c допустимым числом")
    public void factorialWithNumber() {
        assertTrue(Main.getFactorial(5) == 120);
    }
    @Test
    @DisplayName("Проверка функции на отрицательное число")
    public void factorialWithNegativeNumber() {
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            Main.getFactorial(-1);
        });
        assertEquals("Число не может быть отрицательным", excepcion.getMessage());
    }
    @Test
    @DisplayName("Проверка функции на недопустимое число")
    public void factorialWithBigNumber() {
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            Main.getFactorial(32);
        });
        assertEquals("Число не может быть больше 31", excepcion.getMessage());
    }
}
