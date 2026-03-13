package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.annotations.Step;
import screenplay.interactions.SelectFromDropdown;
import screenplay.userinterfaces.KudosFormUI;

public class FillKudoForm implements Task {

    private final String senderEmail;
    private final String recipientEmail;
    private final String category;
    private final String message;

    public FillKudoForm(String senderEmail, String recipientEmail, String category, String message) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.category = category;
        this.message = message;
    }

    public static FillKudoFormBuilder withSender(String senderEmail) {
        return new FillKudoFormBuilder(senderEmail);
    }

    @Override
    @Step("{0} fills the kudo form")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromDropdown.byValue(KudosFormUI.SELECT_FROM, senderEmail),
                SelectFromDropdown.byValue(KudosFormUI.SELECT_TO, recipientEmail),
                SelectFromDropdown.byVisibleText(KudosFormUI.SELECT_CATEGORY, category),
                Enter.theValue(message).into(KudosFormUI.MESSAGE_TEXTAREA)
        );
    }

    public static class FillKudoFormBuilder {
        private final String senderEmail;
        private String recipientEmail;
        private String category;

        public FillKudoFormBuilder(String senderEmail) {
            this.senderEmail = senderEmail;
        }

        public FillKudoFormBuilder andRecipient(String recipientEmail) {
            this.recipientEmail = recipientEmail;
            return this;
        }

        public FillKudoFormBuilder inCategory(String category) {
            this.category = category;
            return this;
        }

        public FillKudoForm withMessage(String message) {
            return new FillKudoForm(senderEmail, recipientEmail, category, message);
        }
    }
}
