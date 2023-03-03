import com.codeborne.selenide.SelenideElement;
import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    public void openUrl(String url) {
        open(url);
    }

}
