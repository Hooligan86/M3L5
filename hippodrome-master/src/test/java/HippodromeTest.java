import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    //A
    @Test
    void constructorThrowExceptionArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }

    @Test
    void constructorThrowExceptionWithRightMessageArgumentIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage() );
    }

    @Test
    void constructorThrowExceptionIfListEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
    }

    @Test
    void constructorThrowExceptionWithRightMessageIfListEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage() );
    }

    //B
    @Test
    void getHorsesReturnsRightList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("name" + i, 2.5, 2.5));
        }
        Hippodrome h = new Hippodrome(horses);
        assertEquals(horses, h.getHorses());
    }

    //C
    @Test
    void callMethodMoveForEachHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome h = new Hippodrome(horses);
        h.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    //D
    @Test
    void methodGetWinnerReturnsHorseWithBiggestDistance() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("name1", 2.5, 10.0));
        horses.add(new Horse("name1", 2.5, 5.0));
        horses.add(new Horse("name1", 2.5, 3.0));
        Hippodrome h = new Hippodrome(horses);
        assertSame(horses.get(0), h.getWinner());
    }


}