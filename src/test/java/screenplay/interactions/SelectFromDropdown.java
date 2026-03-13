package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.support.ui.Select;

public class SelectFromDropdown implements Interaction {

    private final Target dropdown;
    private final String value;
    private final boolean byVisibleText;

    public SelectFromDropdown(Target dropdown, String value, boolean byVisibleText) {
        this.dropdown = dropdown;
        this.value = value;
        this.byVisibleText = byVisibleText;
    }

    public static SelectFromDropdown byValue(Target dropdown, String value) {
        return new SelectFromDropdown(dropdown, value, false);
    }

    public static SelectFromDropdown byVisibleText(Target dropdown, String text) {
        return new SelectFromDropdown(dropdown, text, true);
    }

    @Override
    @Step("{0} selects '#value' from #dropdown")
    public <T extends Actor> void performAs(T actor) {
        var element = dropdown.resolveFor(actor);
        Select select = new Select(element);
        if (byVisibleText) {
            select.selectByVisibleText(value);
        } else {
            select.selectByValue(value);
        }
    }
}
