package pages.elements.common.impl;

import org.openqa.selenium.WebElement;
import pages.elements.common.Element;
import pages.elements.common.ElementFactory;

import java.lang.reflect.InvocationTargetException;

import static java.text.MessageFormat.format;

/**
 * Created on 27.03.2018
 */
public class DefaultElementFactory implements ElementFactory {
    @Override
    public <E extends Element> E create(Class<E> elementClass, WebElement wrappedElement) {
        try {
            return elementClass.getConstructor(WebElement.class).
                    newInstance(wrappedElement);
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + elementClass
            );
        }
    }

    private <E extends Element> Class<? extends E> findImplementationFor(final Class<E> elementClass) {
        try {
            return (Class<? extends E>) Class.forName(format("{0}.{1}",
                    getClass().getPackage().getName(), elementClass.getSimpleName()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
