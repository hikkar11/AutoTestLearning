package ui.demoqa.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.demoqa.pages.SliderPage;

import static com.codeborne.selenide.Selenide.open;

public class SliderTest extends BaseTest{

    @Test
    public void sliderTest(){

        open("https://demoqa.com/slider");

        SliderPage sliderPage = new SliderPage();
        sliderPage.dragSlider(150);
        int sliderValue = sliderPage.getSliderValue();
        int resultValue = sliderPage.getResultValue();

        Assertions.assertEquals(sliderValue, resultValue);
    }
}