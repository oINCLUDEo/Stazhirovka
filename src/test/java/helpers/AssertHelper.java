package helpers;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AssertHelper {
    public static void assertEqualsWithMessage(String actual, String expected, String fieldName) {
        assertEquals(actual, expected,
                String.format("%s не совпадает с ожидаемым. Ожидалось: '%s', получено: '%s'",
                        fieldName, expected, actual));
    }

    public static void assertEqualsWithMessage(String actual, String expected) {
        String defaultFieldName = "Значение";
        assertEqualsWithMessage(actual, expected, defaultFieldName);
    }

    public static void assertTrueWithMessage(boolean condition, String message) {
        assertTrue(condition, message);
    }
}
