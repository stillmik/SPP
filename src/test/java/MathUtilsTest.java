import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
//informs JUnit which method should be run
    void test() {
        System.out.println("this test had been run");
        //fail("not yet implemented");
    }

    @Test
    void test2() {
        System.out.println("and this test also had been run");
        //fail("not yet implemented");
    }

    @Test
    void testMathUtilsAdd() {
        int expected = 8;
        int actual = Main.add(5, 3);
        assertEquals(expected,actual,"this is an add method");
        //assertArrayEquals();
        //assertIterableEquals(); verify if positions are equals
        //assertFalse()
        //

    }

}
