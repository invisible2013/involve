package ge.economy.involve.core.metadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by nino on 5/14/16.
 * if field is marked by this annotation , means, that client is not responsible to pass this var in the request
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SessionVariable {
}
