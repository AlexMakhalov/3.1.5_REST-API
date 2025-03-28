package ru.makhalov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.makhalov.config.MyConfig;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication comm = context.getBean("communication", Communication.class);

        System.out.println(comm.getAllUsers());
        System.out.println(comm.createUser());
        System.out.println(comm.updateUser());
        System.out.println(comm.deleteUser());
        System.out.println(comm.getResponse());


    }
}
