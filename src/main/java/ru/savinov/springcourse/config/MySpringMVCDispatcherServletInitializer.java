package ru.savinov.springcourse.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class}; /*здесь мы укажим класс конфигурации заменяющий applicationContext.xml*/
        // фигурные скобки указываеют где лежит конфигурационный класс
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; // все http запросы посылаем на dispatcherServlet
    }
}
