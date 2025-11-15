package ui.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SliderPage {

    private final SelenideElement slider = $x("//input[@type='range']");
    private final SelenideElement result = $x("//input[@id='sliderValue']");

    public void dragSlider(int moveByPixels) {
        actions()
                .clickAndHold(slider)
                .moveByOffset(moveByPixels, 0)
                .release()
                .perform();
    }

    public int getSliderValue() {
        return Integer.parseInt(slider.getValue());
    }

    public int getResultValue() {
        return Integer.parseInt(result.getValue());
    }
}