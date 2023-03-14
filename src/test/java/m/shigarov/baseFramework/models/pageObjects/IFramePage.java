package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.elements.IFrame;
import org.openqa.selenium.By;

public class IFramePage extends BaseForm {
    private static final By uniqueElementsLocator = By.xpath("//div[@id=\"framesWrapper\"]");
    private static final  String name = "iFramePage";

    public IFramePage() {
        super(uniqueElementsLocator, name);
    }
    By parentFramesLocator = By.xpath("//iframe[@id='frame1']");
    By parentFrameUniqueElementsLocator = By.xpath("//body[text() = 'Parent frame']");
    By childFramesLocator = By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']");
    By childFrameUniqueElementsLocator = By.xpath("//p[text() = 'Child Iframe']");
    By upperFramesLocator = By.xpath("//iframe[@id='frame1']");
    By upperFrameUniqueElementsLocator = By.xpath("//*[@id='sampleHeading']");
    By lowerFramesLocator = By.xpath("//iframe[@id='frame2']");
    By lowerFrameUniqueElementsLocator = By.xpath("//h1[@id='sampleHeading']");

    private final IFrame parentFrame = new IFrame(parentFramesLocator, parentFrameUniqueElementsLocator,"parentFrame");
    private final IFrame childFrame = new IFrame(childFramesLocator, childFrameUniqueElementsLocator, "childFrame");
    private final IFrame upperFrame = new IFrame(upperFramesLocator, upperFrameUniqueElementsLocator, "upperFrame");
    private final IFrame lowerFrame = new IFrame(lowerFramesLocator, lowerFrameUniqueElementsLocator, "lowerFrame");

    public Boolean isParentFrameVisible(Boolean escapeToDefaultContextAfter) {
        return parentFrame.isUniqueElementInside(escapeToDefaultContextAfter);
    }
    public Boolean isChildFrameVisible(Boolean escapeToDefaultContextAfter) {
        return childFrame.isUniqueElementInside(escapeToDefaultContextAfter);
    }
    public String getMessageFromUpperFrame() {
        return upperFrame.getMessageFromInside();
    }
    public String getMessageFromLowerFrame() {
        return lowerFrame.getMessageFromInside();
    }
}
