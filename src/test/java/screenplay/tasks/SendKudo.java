package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.annotations.Step;
import screenplay.interactions.DragSlider;
import screenplay.userinterfaces.KudosFormUI;

public class SendKudo implements Task {

    public static SendKudo byDraggingSlider() {
        return new SendKudo();
    }

    @Override
    @Step("{0} sends the kudo by dragging the slider")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DragSlider.from(KudosFormUI.SLIDER_THUMB, KudosFormUI.SLIDER_CONTAINER)
        );
    }
}
