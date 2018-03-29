package pages.elements.filter.containers.impl;

import org.openqa.selenium.WebElement;
import pages.elements.filter.containers.Container;
import pages.elements.filter.containers.ContainerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Created on 27.03.2018
 */
public class DefaultContainerFactory implements ContainerFactory {

    @Override
    public <C extends Container> C create(Class<C> containerClass, WebElement wrappedElement) {
        final C container = createInstanceOf(containerClass, wrappedElement);
        Objects.requireNonNull(container, "Container is null").init(wrappedElement);
        return container;
    }

    private <C extends Container> C createInstanceOf(Class<C> containerClass, WebElement wrappedElement) {
        try {
            return containerClass.getConstructor(WebElement.class).newInstance(wrappedElement);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
