9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/common/src/test/java/engineering/everest/lhotse/axon/common/services/ReadServiceTest.java
package engineering.everest.lhotse.axon.common.services;

import org.junit.jupiter.api.Test;

import java.lang.reflect.TypeVariable;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadServiceTest {

    @Test
    void getByIdMethodMustExistAndBeGeneric() throws NoSuchMethodException {
        var getByIdMethod = ReadService.class.getMethod("getById", UUID.class);
        assertTrue(getByIdMethod.getGenericReturnType() instanceof TypeVariable);
    }
}
