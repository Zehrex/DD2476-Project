3
https://raw.githubusercontent.com/Silthus/sLimits/master/src/main/java/net/silthus/slib/config/typeconversions/EnumTypeConversion.java
package net.silthus.slib.config.typeconversions;

import java.lang.reflect.Type;

/**
 * @author zml2008
 */
public class EnumTypeConversion extends TypeConversion {

    @Override
    protected Object cast(Class<?> target, Type[] neededGenerics, Object value) {

        try {
            return Enum.valueOf(target.asSubclass(Enum.class), value.toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public boolean isApplicable(Class<?> target, Object value) {

        return target.isEnum();
    }

    @Override
    protected int getParametersRequired() {

        return 0;
    }
}
