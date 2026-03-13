package screenplay.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class KudosFormUI {

    public static final Target SELECT_FROM = Target.the("sender dropdown")
            .located(By.xpath("//*[@data-testid='select-from']"));

    public static final Target SELECT_TO = Target.the("recipient dropdown")
            .located(By.xpath("//*[@data-testid='select-to']"));

    public static final Target SELECT_CATEGORY = Target.the("category dropdown")
            .located(By.xpath("//*[@data-testid='select-category']"));

    public static final Target MESSAGE_TEXTAREA = Target.the("message text area")
            .located(By.xpath("//*[@data-testid='textarea-message']"));

    public static final Target SLIDER_CONTAINER = Target.the("slider container")
            .located(By.xpath("//*[@data-testid='slider-container']"));

    public static final Target SLIDER_THUMB = Target.the("slider thumb")
            .located(By.xpath("//*[@data-testid='slider-thumb']"));
}
