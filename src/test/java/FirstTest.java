import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FirstTest {

    @BeforeAll
    static void beforeAll() {
        //Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    BasePage page = new BasePage();

    @Test
    @DisplayName("firstEnterTest")
    void firstEnterTest() {
        page.openUrl("https://github.com");
        $$("li.HeaderMenu-item")
                .find(text("Solutions"))
                .hover()
                .$(".HeaderMenu-dropdown")
                .$$("ul li")
                .find(text("Enterprise"))
                .click();

        $("div.enterprise-hero").shouldHave(text("GitHub for enterprises"));
    }

    @Test
    @DisplayName("Drag&Drop Test")
    void dadTest() {
        page.openUrl("https://the-internet.herokuapp.com/drag_and_drop");
        SelenideElement a = $x("//*[@id='column-a']");
        SelenideElement b = $x("//*[@id='column-b']");
        a.dragAndDropTo(b);
        a.shouldHave(text("B"));
        b.shouldHave(text("A"));
        closeWindow();
    }
/*тест провален, не тащит элемент, к тому же без закрытия браузера на той же странице мешает выполнению предыдущего теста,
дважды передвигает эелемент*/

    @Test //будет провален, элемент не передвигается
    @DisplayName("Test with actions")
    void actionTest() {
        page.openUrl("https://the-internet.herokuapp.com/drag_and_drop");
        SelenideElement a = $x("//*[@id='column-a']");
        SelenideElement b = $x("//*[@id='column-b']");

        Selenide.actions()
                .clickAndHold(a)
                .moveToElement(b)
                .release()
                .build()
                .perform();

        a.shouldHave(text("B"));
        b.shouldHave(text("A"));
    }

}
