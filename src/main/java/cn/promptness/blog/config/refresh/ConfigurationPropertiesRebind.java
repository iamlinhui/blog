package cn.promptness.blog.config.refresh;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author lynn
 * @date 2020/8/31 20:14
 * @since v1.0.0
 */
@Component
public class ConfigurationPropertiesRebind implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationPropertiesRebind.class);

    private final ConfigurationPropertiesBeans beans;

    private ApplicationContext applicationContext;

    private final Map<String, Exception> errors = new ConcurrentHashMap<>();

    public ConfigurationPropertiesRebind(ConfigurationPropertiesBeans beans) {
        this.beans = beans;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * A map of bean name to errors when instantiating the bean.
     *
     * @return The errors accumulated since the latest destroy.
     */
    public Map<String, Exception> getErrors() {
        return this.errors;
    }

    public void rebind() {
        this.errors.clear();
        for (String name : this.beans.getBeanNames()) {
            rebind(name);
        }
    }

    public boolean rebind(String name) {
        if (!this.beans.getBeanNames().contains(name)) {
            return false;
        }
        if (this.applicationContext != null) {
            try {
                Object bean = this.applicationContext.getBean(name);
                if (AopUtils.isAopProxy(bean)) {
                    bean = getTargetObject(bean);
                }
                if (bean != null) {
                    this.applicationContext.getAutowireCapableBeanFactory().destroyBean(bean);
                    this.applicationContext.getAutowireCapableBeanFactory().initializeBean(bean, name);
                    logger.info("@ConfigurationProperties {} Rebind Success", name);
                    return true;
                }
            } catch (RuntimeException e) {
                this.errors.put(name, e);
                throw e;
            } catch (Exception e) {
                this.errors.put(name, e);
                throw new IllegalStateException("Cannot rebind to " + name, e);
            }
        }
        return false;
    }

    public Set<String> getBeanNames() {
        return new HashSet<>(this.beans.getBeanNames());
    }


    @SuppressWarnings("unchecked")
    public <T> T getTargetObject(Object candidate) {
        try {
            if (AopUtils.isAopProxy(candidate) && (candidate instanceof Advised)) {
                return (T) ((Advised) candidate).getTargetSource().getTarget();
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to unwrap proxied object", ex);
        }
        return (T) candidate;
    }

}
