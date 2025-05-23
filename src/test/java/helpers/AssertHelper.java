package helpers;

import static org.testng.Assert.assertEquals;

public class AssertHelper {
    public static void assertEqualsWithMessage(String expected, String actual, String fieldName) {
        assertEquals(actual, expected,
                String.format("%s не совпадает с ожидаемым. Ожидалось: '%s', получено: '%s'",
                        fieldName, expected, actual));
    }

    public static void assertEqualsWithMessage(String expected, String actual) {
        String defaultFieldName = "Значение";
        assertEqualsWithMessage(expected, actual, defaultFieldName);
    }
}
