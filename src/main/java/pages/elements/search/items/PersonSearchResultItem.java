package pages.elements.search.items;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.page_elements.containers.impl.AbstractContainer;

/**
 * Created on 25.03.2018
 */
public class PersonSearchResultItem extends AbstractContainer {

    @FindBy(className = "search-result__actions--primary")
    private WebElement connectButton;

    @FindBy(className = "search-result__result-link")
    private WebElement contactLink;

    @FindBy(className = "actor-name")
    private WebElement personName;

    @FindBy(className = "subline-level-1")
    private WebElement personProfession;

    @FindBy(className = "subline-level-2")
    private WebElement personLocation;

    @FindBy(className = "search-result__snippets")
    private WebElement personAdditionalInfo;

    public WebElement getConnectButton() {
        return connectButton;
    }

    public WebElement getPersonName() {
        return personName;
    }

    public WebElement getContactLink() {
        return contactLink;
    }

    public WebElement getPersonProfession() {
        return personProfession;
    }

    public WebElement getPersonAdditionalInfo() {
        return personAdditionalInfo;
    }

    public WebElement getPersonLocation() {
        return personLocation;
    }
}
