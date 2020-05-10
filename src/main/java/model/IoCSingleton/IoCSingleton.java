package model.IoCSingleton;

import configuration.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IoCSingleton {
    static private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
