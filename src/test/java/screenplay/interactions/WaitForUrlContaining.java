package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitForUrlContaining implements Interaction {

    private final String urlFragment;

    public WaitForUrlContaining(String urlFragment) {
        this.urlFragment = urlFragment;
    }

    public static WaitForUrlContaining text(String urlFragment) {
        return new WaitForUrlContaining(urlFragment);
    }

    @Override
    @Step("{0} waits for the URL to contain '#urlFragment'")
    public <T extends Actor> void performAs(T actor) {
        new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains(urlFragment));
    }
}
