package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import screenplay.questions.IsKudoDisplayed;
import screenplay.tasks.FillKudoForm;
import screenplay.tasks.NavigateToKudos;
import screenplay.tasks.OpenApplication;
import screenplay.tasks.SendKudo;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class SendKudoStepDefinitions {

    private Actor actor;
    private String senderEmail;
    private String recipientEmail;
    private String category;
    private String message;

    private Actor getActor() {
        if (actor == null) {
            OnStage.setTheStage(new OnlineCast());
            actor = OnStage.theActorCalled("collaborator");
        }
        return actor;
    }

    @Given("the collaborator is on the main page")
    public void elColaboradorNavegaALaPaginaPrincipal() {
        String baseUrl = System.getProperty("webdriver.base.url", "http://localhost:5173");
        getActor().attemptsTo(
                OpenApplication.atUrl(baseUrl)
        );
    }

    @When("they access the Kudos section")
    public void accedeALaSeccionDeKudos() {
        getActor().attemptsTo(
                NavigateToKudos.section()
        );
    }

    @When("they select {string} as sender")
    public void seleccionaComoRemitente(String email) {
        this.senderEmail = email;
    }

    @When("they select {string} as recipient")
    public void seleccionaComoDestinatario(String email) {
        this.recipientEmail = email;
    }

    @When("they select the category {string}")
    public void seleccionaLaCategoria(String cat) {
        this.category = cat;
    }

    @When("they write the message {string}")
    public void escribeElMensaje(String msg) {
        this.message = msg;
        getActor().attemptsTo(
                FillKudoForm.withSender(senderEmail)
                        .andRecipient(recipientEmail)
                        .inCategory(category)
                        .withMessage(message)
        );
    }

    @When("they drag the slider to send the kudo")
    public void deslizaElSliderParaEnviar() {
        getActor().attemptsTo(
                SendKudo.byDraggingSlider()
        );
    }

    @Then("the kudo with message {string} appears in the kudos list")
    public void elKudoApareceEnLaLista(String expectedMessage) throws InterruptedException {
        Thread.sleep(3000);
        String baseUrl = System.getProperty("webdriver.base.url", "http://localhost:5173");
        BrowseTheWeb.as(getActor()).getDriver().get(baseUrl + "/kudos/list");

        getActor().should(
                seeThat(IsKudoDisplayed.withMessage(expectedMessage), is(true))
        );
    }
}
