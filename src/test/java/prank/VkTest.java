package prank;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ui.demoqa.tests.BaseTest;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class VkTest extends BaseTest {
    private final static String BASE_URL = "https://vk.com/feed";

    private final SelenideElement massangerButton = $x("//*[text()='Мессенджер']");
    private final SelenideElement elinaLs = $x("//*[text()='Elina Gizatullina']");
    private final SelenideElement inputButton = $x("//span[@data-placeholder='Сообщение']");

    @Test
    public void vkHello() throws InterruptedException {
        open(BASE_URL);

        System.out.println(">>> ТЕСТ ОСТАНОВЛЕН: Пожалуйста, подтвердите вход через QR-код. У вас 25 секунд.");

        Thread.sleep(25000);

        System.out.println(">>> ТЕСТ ПРОДОЛЖАЕТСЯ...");

        massangerButton.click();

        elinaLs.click();

        inputButton.setValue("");

        inputButton.sendKeys(Keys.ENTER);
    }
}
