package pages.elements.filter.containers;

import org.openqa.selenium.WebElement;
import pages.elements.common.Element;

/**
 * Created on 27.03.2018
 */
public interface Container extends Element {

    void init(WebElement wrappedElement);
}
