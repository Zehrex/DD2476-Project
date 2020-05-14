9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/organizations-api/src/test/java/engineering/everest/lhotse/organizations/OrganizationTest.java
package engineering.everest.lhotse.organizations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrganizationTest {

    @Test
    void OrganizationClassMustExist() throws ClassNotFoundException {
        final Class<?> organizationClass = Class.forName(getClass().getPackageName() + ".Organization");
        assertNotNull(organizationClass);
    }

}