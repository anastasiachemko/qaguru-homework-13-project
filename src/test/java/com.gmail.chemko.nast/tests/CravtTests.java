package com.gmail.chemko.nast.tests;

import com.codeborne.selenide.Condition;
import com.gmail.chemko.nast.helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class CravtTests extends TestBase {

    @Test
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open url 'https://shop.cravt.by/'", () -> {
                open("https://shop.cravt.by/");
    });

        step("Page title should have text 'Кравт - интернет-магазин парфюмерии и косметики - KPABT'", () -> {
            String expectedTitle = "Кравт - интернет-магазин парфюмерии и косметики - KPABT";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Logo should be visible")
    void logoTest() {
        step("Open the main page", () -> {
            open("https://shop.cravt.by");
        });

        step("Check that the logo is visible", () -> {
            $(".logo").shouldBe(Condition.visible);
        });
    }

    @Test
    @DisplayName("Layout aside panel should be visible")
    void panelTest() {
        step("Open the main page", () -> {
            open("https://shop.cravt.by");
        });

        step("Click on the dropdown 'Маякияж'", () -> {
            $(byText("Макияж")).click();
        });

        step("Check that the layout aside panel is visible", () -> {
            $(".layout__aside").shouldBe(Condition.visible);
        });
    }

    @Test
    @DisplayName("Search results should appear")
    void searchTest() {
        step("Open the main page", () -> {
                open("https://shop.cravt.by/");
    });
        step("Open the main page", () -> {
            $(".top-search-lite-box__input").setValue("Gucci").pressEnter();
    });
        $(".page-title").shouldHave(text("Результаты по запросу «Gucci»"));
    }


    @Test
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open the main page", () ->
                open("https://shop.cravt.by/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}
