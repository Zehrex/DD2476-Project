9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/api/src/main/java/engineering/everest/lhotse/api/rest/annotations/AdminOrUserOfTargetOrganization.java
package engineering.everest.lhotse.api.rest.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize("hasRole('ADMIN') or #requestingUser.organizationId == #organizationId")
public @interface AdminOrUserOfTargetOrganization {
}
