package exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// BEGIN
@Component
class CustomBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private static final Map<String, Object> beansConfiguration = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            beansConfiguration.put("level", level);
            beansConfiguration.put("bean", bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object reqBean = beansConfiguration.get("bean");
        if (bean.equals(reqBean)) {
            Class beanClass = reqBean.getClass();
            return Proxy.newProxyInstance(
                    beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        String message = String.format("Was called method: %s() with arguments: %s", method.getName(), Arrays.toString(args));
                        if (beansConfiguration.get("level").equals("info")) {
                            LOGGER.info(message);
                        }
                        if (beansConfiguration.get("level").equals("debug")) {
                            LOGGER.debug(message);
                        }
                        return method.invoke(bean, args);
                    }
            );
        }
        return bean;
    }
}
// END
