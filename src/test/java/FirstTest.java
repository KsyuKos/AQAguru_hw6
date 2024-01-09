import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.manager.SeleniumManager;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirstTest {

    @BeforeAll
    static void beforeAll() {
        //Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    BasePage page = new BasePage();

    @Test
    @DisplayName("Test hover")
    @Order(1)
    public void testHover() {
        page.openUrl("https://github.com");
        $$("li.HeaderMenu-item")
                .find(text("Solutions"))
                .hover()
                .$(".HeaderMenu-dropdown")
                .$$("ul li")
                .find(text("Enterprise"))
                .click();

        $("section.enterprise-hero").shouldHave(text("The AI-powered"));
    }

    @Test
    @DisplayName("Test Drag & Drop")
    @Disabled
    @Order(2)
    public void testDed() {
        page.openUrl("https://the-internet.herokuapp.com/drag_and_drop");
        SelenideElement a = $x("//*[@id='column-a']");
        SelenideElement b = $x("//*[@id='column-b']");
        a.dragAndDropTo(b);
        a.shouldHave(text("B"));
        b.shouldHave(text("A"));
    }

    @Test //будет провален, элемент не передвигается
    @DisplayName("Test actions")
    @Disabled
    @Order(3)
    void testActions() {
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
