package cn.promptness.blog.config.refresh;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author : Lynn
 * @date : 2019-05-27 22:49
 */
@Component
public class ConfigurationPropertiesRefresher implements EnvironmentAware {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationPropertiesRefresher.class);

    private static final String ROOT = "root";

    private static final Bindable<Map<String, String>> STRING_STRING_MAP = Bindable.mapOf(String.class, String.class);

    private final ConfigurationPropertiesRebind configurationPropertiesRebind;

    public ConfigurationPropertiesRefresher(ConfigurationPropertiesRebind configurationPropertiesRebind) {
        this.configurationPropertiesRebind = configurationPropertiesRebind;
    }

    @ApolloConfigChangeListener(value = "application")
    public void onChange(ConfigChangeEvent changeEvent) {
        configurationPropertiesRebind.rebind();
        if (changeEvent != null) {
            LoggingSystem system = LoggingSystem.get(LoggingSystem.class.getClassLoader());
            setLogLevels(system, this.environment);
        }
    }


    protected void setLogLevels(LoggingSystem system, Environment environment) {
        Map<String, String> levels = Binder.get(environment).bind("logging.level", STRING_STRING_MAP).orElseGet(Collections::emptyMap);
        for (Map.Entry<String, String> entry : levels.entrySet()) {
            setLogLevel(system, environment, entry.getKey(), entry.getValue());
        }
    }

    private void setLogLevel(LoggingSystem system, Environment environment, String name, String level) {
        try {
            if (name.equalsIgnoreCase(ROOT)) {
                name = null;
            }
            level = environment.resolvePlaceholders(level);
            system.setLogLevel(name, LogLevel.valueOf(level.toUpperCase()));
        } catch (RuntimeException ex) {
            logger.error("Cannot set level: " + level + " for '" + name + "'");
        }
    }

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
