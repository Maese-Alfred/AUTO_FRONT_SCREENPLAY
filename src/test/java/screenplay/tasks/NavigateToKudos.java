package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.annotations.Step;
import screenplay.interactions.WaitForUrlContaining;
import screenplay.userinterfaces.KudosFormUI;
import screenplay.userinterfaces.NavBarUI;

public class NavigateToKudos implements Task {

    public static NavigateToKudos section() {
        return new NavigateToKudos();
    }

    @Override
    @Step("{0} navigates to the Kudos section")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(NavBarUI.ACCEDER_BUTTON),
                WaitForUrlContaining.text("/kudos")
        );
    }
}
