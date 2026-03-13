package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IsKudoDisplayed implements Question<Boolean> {

    private final String expectedMessage;

    public IsKudoDisplayed(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    public static IsKudoDisplayed withMessage(String message) {
        return new IsKudoDisplayed(message);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriverWait wait = new WebDriverWait(
                BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(20)
        );
        return wait.until(driver -> {
            try {
                List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
                return rows.stream().anyMatch(row -> {
                    try {
                        return row.getText().contains(expectedMessage);
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }
                });
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }
}
