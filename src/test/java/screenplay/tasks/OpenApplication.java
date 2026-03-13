package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Step;

public class OpenApplication implements Task {

    private final String url;

    public OpenApplication(String url) {
        this.url = url;
    }

    public static OpenApplication atUrl(String url) {
        return new OpenApplication(url);
    }

    @Override
    @Step("{0} opens the application")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().get(url);
    }
}
