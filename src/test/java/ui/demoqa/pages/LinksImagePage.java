package ui.demoqa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$x;

public class LinksImagePage {

    private final SelenideElement validImage = $x("//p[text()='Valid image']/following-sibling::img[1]");

    public int getValidImageResponseCode() throws IOException {
        validImage.shouldBe(Condition.visible);
        String absoluteUrl = validImage.getAttribute("src");

        if (absoluteUrl == null || absoluteUrl.isEmpty()) {
            throw new IllegalStateException("Could not get absolute URL for image source.");
        }

        URL url = new URL(absoluteUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        return responseCode;
    }
}