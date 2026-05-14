package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class SectionConstructorTest extends BaseTest {

    @Test
    @DisplayName("Работает переходы к разделам: «Булки»")
    @Description("Ожидаем текст Булки")
    public void shouldTransitionsSectionsBuns() throws InterruptedException {
        objMainPage.clickSauces(); // булки по умолчанию первые
        objMainPage.clickBuns();
        objMainPage.attributeContainsBuns();

        String expected = "Булки";
        String actual = objMainPage.getTextBuns();
        Assert.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Работает переходы к разделам:«Соусы»")
    @Description("Ожидаем текст Соусы")
    public void shouldTransitionsSectionsSauces() throws InterruptedException {
        objMainPage.clickSauces();
        objMainPage.attributeContainsSauces();

        String expected = "Соусы";
        String actual = objMainPage.getTextSauces();
        Assert.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Работает переходы к разделам:«Начинки»")
    @Description("Ожидаем текст Начинки")
    public void shouldTransitionsSectionsFillings() throws InterruptedException {
        objMainPage.clickFillings();
        objMainPage.attributeContainsFillings();

        String expected = "Начинки";
        String actual = objMainPage.getTextFillings();
        Assert.assertEquals(expected, actual);
    }

}