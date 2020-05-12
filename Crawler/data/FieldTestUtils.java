6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/FieldTestUtils.java
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

import static org.junit.Assert.*;

public final class FieldTestUtils {

    private FieldTestUtils() {
        throw new UnsupportedOperationException("Constructor is not to be used for static utils collection class.");
    }

    private static void checkObjectAndTypeParameters(Object obj, Class<?> type, String fieldName) {
        Objects.requireNonNull(obj, "Given object must not be null.");
        Objects.requireNonNull(type, "Given class type must not be null.");
        Objects.requireNonNull(fieldName, "Given field name must not be null.");
    }

    private static void checkObjectParameters(Object getObject, String fieldName) {
        Objects.requireNonNull(getObject, "Given object must not be null.");
        Objects.requireNonNull(fieldName, "Given field name must not be null.");
    }

    public static Object getPrivateField(Object getObj, Class<?> type, String fieldName) {
        checkObjectAndTypeParameters(getObj, type, fieldName);

        try {
            Field field = type.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(getObj);
        } catch (Exception e) {
            System.err.println("ERROR: retrieving private int field " + fieldName + ": " + e);
            return null;
        }
    }

    public static void setPrivateField(Object setObj, Class<?> type, String fieldName, Object value) {
        checkObjectAndTypeParameters(setObj, type, fieldName);

        try {
            Field field = type.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(setObj, value);
        } catch (Exception e) {
            System.err.println("ERROR: setting private field " + fieldName + ": " + e);
        }
    }

    public static void checkFieldType(Object checkObj, Class<?> type, String fieldName) {
        checkObjectAndTypeParameters(checkObj, type, fieldName);

        try {
            Field field = checkObj.getClass().getDeclaredField(fieldName);
            Class<?> fClass = field.getType();
            assertEquals(fieldName + " has unexpected type.", type, fClass);
        } catch (NoSuchFieldException e) {
            String message = checkObj.getClass() + " must have field called " + fieldName;
            System.err.println(message);
            fail(message);
        }
    }

    public static void checkFieldAccessModifier(Object checkObj, int modifierType, String fieldName) {
        checkObjectParameters(checkObj, fieldName);

        try {
            Field field = checkObj.getClass().getDeclaredField(fieldName);
            int modifierId = field.getModifiers();
            switch (modifierType) {
                case Modifier.PRIVATE:
                    assertTrue(fieldName + " must be private", Modifier.isPrivate(modifierId));
                    break;
                case Modifier.PROTECTED:
                    assertTrue(fieldName + " must be private", Modifier.isProtected(modifierId));
                    break;
                case Modifier.PUBLIC:
                    assertTrue(fieldName + " must be private", Modifier.isPublic(modifierId));
                    break;
                default:
                    throw new RuntimeException("Unhandled modifier type: " + modifierType);
            }
        } catch (NoSuchFieldException e) {
            String message = checkObj.getClass() + " must have field called " + fieldName;
            System.err.println(message);
            fail(message);
        }
    }

    public static void checkFieldFinalModifier(Object checkObj, boolean isFinal, String fieldName) {
        checkObjectParameters(checkObj, fieldName);

        try {
            Field field = checkObj.getClass().getDeclaredField(fieldName);
            int modifierId = field.getModifiers();
            assertEquals(fieldName + " must " + (isFinal ? "" : "not ") + "be final", isFinal,
                    Modifier.isFinal(modifierId));
        } catch (NoSuchFieldException e) {
            String message = checkObj.getClass() + " must have field called " + fieldName;
            System.err.println(message);
            fail(message);
        }
    }

    public static void checkFieldStaticModifier(Object checkObj, boolean isStatic, String fieldName) {
        checkObjectParameters(checkObj, fieldName);

        try {
            Field field = checkObj.getClass().getDeclaredField(fieldName);
            int modifierId = field.getModifiers();
            assertEquals(fieldName + " must " + (isStatic ? "" : "not ") + "be static", isStatic,
                    Modifier.isStatic(modifierId));
        } catch (NoSuchFieldException e) {
            String message = checkObj.getClass() + " must have field called " + fieldName;
            System.err.println(message);
            fail(message);
        }
    }
}
