package ru.savinov.springcourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration // данный класс является конфигурационным и заменяет applicationContext.xml
@ComponentScan("ru.savinov.springcourse") //аннотация для сканирования компонентов, для неё необходимо местоположение компонентов (контроллер так же компонент)
@EnableWebMvc // эта аннотация равносильна тегу <mvc:annotation-driven/> - включает необходимые аннотации необходимые для spring MVC приложения
public class SpringConfig implements WebMvcConfigurer { //этот интерфейс используется когда мы под себя хотим настроить spring mvc
    // в нашем случае мы вместо стандартного шаблонизатора будем использовать шаблонизатор thymeleaf

    private final ApplicationContext applicationContext;

    @Autowired //внедряем applicationContext для в переменную для того чтобы внедрить в templateResolver опять же как настройка thymeleaf
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/"); // задаем путь где лежат представления
        templateResolver.setSuffix(".html"); //задаем расширение используемых представлений
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() { // этот бин производит конфигурации представлений
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) { // здесь мы объявляем спрингу о том что мы используем шаблонизатор thymeleaf
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}


