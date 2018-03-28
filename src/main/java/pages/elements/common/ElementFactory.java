package pages.elements.common;

import org.openqa.selenium.WebElement;

/**
 * Created on 27.03.2018
 */
public interface ElementFactory {
    <E extends Element> E create(Class<E> elementClass, WebElement wrappedElement);
}
