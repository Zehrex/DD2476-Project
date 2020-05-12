6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/BookEntryTestUtils.java
import java.util.Objects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public final class BookEntryTestUtils {

    private BookEntryTestUtils() {
        throw new UnsupportedOperationException("Constructor is not to be used for static utils collection class.");
    }

    public static void checkBookFieldValues(BookEntry actualBook, String[] bookEntryFieldNames,
            Object[] expectedFieldValues) {
        Objects.requireNonNull(actualBook, "Given book entry should not be null.");
        Objects.requireNonNull(expectedFieldValues, "Given book field values should not be null.");
        if (bookEntryFieldNames.length != expectedFieldValues.length) {
            throw new IllegalArgumentException("Given field values must have same length as field names.");
        }

        for (int i = 0; i < bookEntryFieldNames.length; i++) {
            String fieldName = bookEntryFieldNames[i];
            Object expected = expectedFieldValues[i];
            Object actual = FieldTestUtils.getPrivateField(actualBook, actualBook.getClass(), fieldName);

            // handle array types specifically so assertArrayEquals can be used
            if (actual instanceof String[]) {
                String[] expectedSpecific = (String[]) expected;
                String[] actualSpecific = (String[]) actual;
                assertArrayEquals(fieldName + " not initialised as expected.", expectedSpecific, actualSpecific);
            } else {
                assertEquals(fieldName + " not initialised as expected.", expected, actual);
            }
        }
    }
}