package surevil.FileSystem.entity.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Column {
    /**
     * column name
     *
     * @return
     */
    String name() default "";
}

