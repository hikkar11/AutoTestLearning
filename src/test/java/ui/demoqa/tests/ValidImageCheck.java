package ui.demoqa.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.demoqa.pages.ElementsPage;
import ui.demoqa.pages.LinksImagePage;
import ui.demoqa.pages.MainPage;

import java.io.IOException;

public class ValidImageCheck extends BaseTest {

    private final static String BASE_URL = "https://demoqa.com/";

   @Test
   @Tag("regression")
    public void validImageCheck() throws IOException {
       MainPage mainPage = new MainPage(BASE_URL);
       mainPage.clickButton();

       ElementsPage elementsPage = new  ElementsPage();
       elementsPage.clickBrokenLinksButton();

       LinksImagePage  linksImagePage = new LinksImagePage();

       int responseCode = linksImagePage.getValidImageResponseCode();

       Assertions.assertEquals(200, responseCode);
   }

}