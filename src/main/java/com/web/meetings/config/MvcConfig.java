package com.web.meetings.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Model-View-Controller framework:
 * Map a URL path or pattern to a view controller to render a response with
 * the configured status code and view.
 */
@Configuration // Indicates that this class may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime
public class MvcConfig implements WebMvcConfigurer {

    /**
     *"String pattern -> path" matching configuration
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home"); //Path to match trailing slash -> (home page)
        registry.addViewController("/home").setViewName("home"); //Path to match "/home" -> (home page)
    }
}
