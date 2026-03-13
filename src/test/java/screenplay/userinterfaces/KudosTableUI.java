package screenplay.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class KudosTableUI {

    public static final Target TABLE_ROWS = Target.the("kudos table rows")
            .located(By.xpath("//table//tbody//tr"));
}
