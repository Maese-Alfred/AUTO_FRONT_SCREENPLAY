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

    @Given("que el colaborador se encuentra en la pagina principal")
    public void elColaboradorNavegaALaPaginaPrincipal() {
        String baseUrl = System.getProperty("webdriver.base.url", "http://localhost:5173");
        getActor().attemptsTo(
                OpenApplication.atUrl(baseUrl)
        );
    }

    @When("accede a la sección de Kudos")
    public void accedeALaSeccionDeKudos() {
        getActor().attemptsTo(
                NavigateToKudos.section()
        );
    }

    @When("selecciona como remitente {string}")
    public void seleccionaComoRemitente(String email) {
        this.senderEmail = email;
    }

    @When("selecciona como destinatario {string}")
    public void seleccionaComoDestinatario(String email) {
        this.recipientEmail = email;
    }

    @When("selecciona la categoría {string}")
    public void seleccionaLaCategoria(String cat) {
        this.category = cat;
    }

    @When("escribe el mensaje {string}")
    public void escribeElMensaje(String msg) {
        this.message = msg;
        getActor().attemptsTo(
                FillKudoForm.withSender(senderEmail)
                        .andRecipient(recipientEmail)
                        .inCategory(category)
                        .withMessage(message)
        );
    }

    @When("desliza el slider para enviar el kudo")
    public void deslizaElSliderParaEnviar() {
        getActor().attemptsTo(
                SendKudo.byDraggingSlider()
        );
    }

    @Then("el kudo con mensaje {string} aparece en la lista de kudos")
    public void elKudoApareceEnLaLista(String expectedMessage) throws InterruptedException {
        Thread.sleep(3000);
        String baseUrl = System.getProperty("webdriver.base.url", "http://localhost:5173");
        BrowseTheWeb.as(getActor()).getDriver().get(baseUrl + "/kudos/list");

        getActor().should(
                seeThat(IsKudoDisplayed.withMessage(expectedMessage), is(true))
        );
    }
}
