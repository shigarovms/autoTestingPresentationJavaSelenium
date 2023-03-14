package m.shigarov.baseFramework.models.elements;

import m.shigarov.baseFramework.models.BaseElement;
import org.openqa.selenium.By;

public class Button extends BaseElement {
    public Button(By locator, String name) {
        super(locator, name);
    }

}
