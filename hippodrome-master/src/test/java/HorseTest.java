import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    //A
    @Test
    void constructorThrowExceptionIfNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 2.5, 2.5);
        });
    }

    @Test
    void constructorThrowExceptionWithRightMessageIfNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 2.5, 2.5);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"\n", " ", "\t", "\r"})
    void constructorThrowExceptionIfNameWithWrongArgs(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 2.5, 2.5);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"\n", " ", "\t", "\r"})
    void constructorThrowExceptionWithRightMessageIfNameWithWrongArgs(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 2.5, 2.5);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorThrowExceptionIfSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", -2.5, 2.5);
        });
    }

    @Test
    void constructorThrowExceptionWithRightMessageIfSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", -2.5, 2.5);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorThrowExceptionIfDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 2.5, -2.5);
        });
    }

    @Test
    void constructorThrowExceptionWithRightMessageIfDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 2.5, -2.5);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    //B
    @Test
    void getRightName() {
        Horse horse = new Horse("name", 2.5, 2.5);
        assertEquals("name", horse.getName());
    }

    //C
    @Test
    void getRightSpeed() {
        Horse horse = new Horse("name", 2.5, 2.5);
        assertEquals(2.5, horse.getSpeed());
    }

    //D
    @Test
    void getRightDistance() {
        Horse horse = new Horse("name", 2.5, 2.5);
        assertEquals(2.5, horse.getDistance());
    }

    @Test
    void getDistanceReturnZeroIfConstructorWithTwoArgs() {
        Horse horse = new Horse("name", 2.5);
        assertEquals(0, horse.getDistance());
    }

    //E
    @Test
    void getRandomDoubleWithRightArgs() {
        try (MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)) {
            new Horse("name", 2.5, 2.5).move();
            horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3})
    void moveReturnRightResult(double value) {
        try (MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 3, 4);
            horseMock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            horse.move();
            assertEquals(4 + 3 * value, horse.getDistance());

        }
    }
}