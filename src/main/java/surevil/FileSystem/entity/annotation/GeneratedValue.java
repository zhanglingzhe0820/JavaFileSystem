package surevil.FileSystem.entity.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface GeneratedValue {
    GenerationType strategy();
}
