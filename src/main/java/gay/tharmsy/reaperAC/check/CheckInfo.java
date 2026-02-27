package gay.tharmsy.reaperAC.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckInfo {
    String name();
    String type() default "A";
    CheckType category();
    String description() default "No description provided.";
    int maxVl() default 20;
}
