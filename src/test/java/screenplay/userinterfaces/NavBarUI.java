package screenplay.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class NavBarUI {

    public static final Target ACCEDER_BUTTON = Target.the("Acceder button")
            .located(By.xpath("//button[normalize-space(text())='Acceder']"));

    public static final Target EXPLORAR_KUDOS_BUTTON = Target.the("Explorar Kudos button")
            .located(By.xpath("//button[normalize-space(text())='Explorar Kudos']"));
}
