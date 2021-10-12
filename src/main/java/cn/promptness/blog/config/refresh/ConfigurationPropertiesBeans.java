package cn.promptness.blog.config.refresh;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lynn
 * @date 2020/8/31 20:47
 * @since v1.0.0
 */
@Component
public class ConfigurationPropertiesBeans implements BeanPostProcessor, ApplicationContextAware {

    private ConfigurationBeanFactoryMetadata metaData;

    private final Map<String, Object> beans = new HashMap<>();

    public ConfigurationPropertiesBeans(ConfigurationBeanFactoryMetadata metaData) {
        this.metaData = metaData;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (applicationContext.getParent() != null && applicationContext.getParent().getAutowireCapableBeanFactory() instanceof ConfigurableListableBeanFactory) {
            ConfigurableListableBeanFactory listable = (ConfigurableListableBeanFactory) applicationContext.getParent().getAutowireCapableBeanFactory();
            String[] names = listable.getBeanNamesForType(ConfigurationPropertiesBeans.class);
            if (names.length == 1) {
                ConfigurationPropertiesBeans parent = (ConfigurationPropertiesBeans) listable.getBean(names[0]);
                this.beans.putAll(parent.beans);
            }
        }
    }

    /**
     * @param beans The bean meta data to set.
     */
    public void setBeanMetaDataStore(ConfigurationBeanFactoryMetadata beans) {
        this.metaData = beans;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        ConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), ConfigurationProperties.class);
        if (annotation != null) {
            this.beans.put(beanName, bean);
        } else if (this.metaData != null) {
            annotation = this.metaData.findFactoryAnnotation(beanName, ConfigurationProperties.class);
            if (annotation != null) {
                this.beans.put(beanName, bean);
            }
        }
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Set<String> getBeanNames() {
        return new HashSet<>(this.beans.keySet());
    }

}

