package uz.elmurodov.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Context {
    public static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MvcConfig.class);
}
