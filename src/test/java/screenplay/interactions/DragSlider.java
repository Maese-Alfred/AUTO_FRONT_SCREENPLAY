package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragSlider implements Interaction {

    private final Target container;
    private final Target thumb;

    public DragSlider(Target container, Target thumb) {
        this.container = container;
        this.thumb = thumb;
    }

    public static DragSlider from(Target thumb, Target container) {
        return new DragSlider(container, thumb);
    }

    @Override
    @Step("{0} drags the slider thumb to complete the action")
    public <T extends Actor> void performAs(T actor) {
        WebElement containerElement = container.resolveFor(actor);
        WebElement thumbElement = thumb.resolveFor(actor);

        int containerWidth = containerElement.getSize().getWidth();
        int thumbWidth = thumbElement.getSize().getWidth();
        int dragDistance = containerWidth - thumbWidth - 5;

        new Actions(BrowseTheWeb.as(actor).getDriver())
                .clickAndHold(thumbElement)
                .moveByOffset(dragDistance, 0)
                .release()
                .perform();
    }
}
