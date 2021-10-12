package cn.promptness.blog.config.refresh;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lynn
 * @date 2020/8/31 21:08
 * @since v1.0.0
 */
@Component
public class ConfigurationBeanFactoryMetadata implements BeanFactoryPostProcessor {

    private ConfigurableListableBeanFactory beanFactory;

    private final Map<String, FactoryMetadata> beansFactoryMetadata = new HashMap<>();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        for (String name : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition definition = beanFactory.getBeanDefinition(name);
            String method = definition.getFactoryMethodName();
            String bean = definition.getFactoryBeanName();
            if (method != null && bean != null) {
                this.beansFactoryMetadata.put(name, new FactoryMetadata(bean, method));
            }
        }
    }

    public <A extends Annotation> A findFactoryAnnotation(String beanName, Class<A> type) {
        Method method = findFactoryMethod(beanName);
        return (method != null) ? AnnotationUtils.findAnnotation(method, type) : null;
    }

    public Method findFactoryMethod(String beanName) {
        if (!this.beansFactoryMetadata.containsKey(beanName)) {
            return null;
        }
        final AtomicReference<Method> found = new AtomicReference<>(null);
        FactoryMetadata metadata = this.beansFactoryMetadata.get(beanName);
        Class<?> factoryType = this.beanFactory.getType(metadata.getBean());
        final String factoryMethod = metadata.getMethod();
        if (ClassUtils.isCglibProxyClass(factoryType)) {
            factoryType = factoryType.getSuperclass();
        }
        ReflectionUtils.doWithMethods(factoryType, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                if (method.getName().equals(factoryMethod)) {
                    found.compareAndSet(null, method);
                }
            }
        });
        return found.get();
    }

    private static class FactoryMetadata {

        private final String bean;

        private final String method;

        FactoryMetadata(String bean, String method) {
            this.bean = bean;
            this.method = method;
        }

        public String getBean() {
            return this.bean;
        }

        public String getMethod() {
            return this.method;
        }

    }

}
