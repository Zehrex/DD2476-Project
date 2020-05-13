13
https://raw.githubusercontent.com/javamxd/ssssssss/master/src/main/java/org/ssssssss/validator/NotNullValidator.java
package org.ssssssss.validator;

import org.w3c.dom.Node;

public class NotNullValidator implements IValidator {
    @Override
    public String support() {
        return "not-null";
    }

    @Override
    public boolean validate(Object input, Node node) {
        return input != null;
    }
}
