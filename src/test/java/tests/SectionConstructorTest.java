package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class SectionConstructorTest extends BaseTest {

    @Test
    @DisplayName("Работает переходы к разделам: «Булки»")
    public void shouldTransitionsSectionsBuns() throws InterruptedException {
        objMainPage.invisibilityAnimation(); // Ожидание проигрывания анимации
        objMainPage.clickSauces(); // булки по умолчанию первые
        objMainPage.clickBuns();
        objMainPage.attributeContainsBuns();
    }

    @Test
    @DisplayName("Работает переходы к разделам:«Соусы»")
    public void shouldTransitionsSectionsSauces() throws InterruptedException {
        objMainPage.invisibilityAnimation(); // Ожидание проигрывания анимации
        objMainPage.clickSauces();
        objMainPage.attributeContainsSauces();
    }

    @Test
    @DisplayName("Работает переходы к разделам:«Начинки»")
    public void shouldTransitionsSectionsFillings() throws InterruptedException {
        objMainPage.invisibilityAnimation(); // Ожидание проигрывания анимации
        objMainPage.clickFillings();
        objMainPage.attributeContainsFillings();
    }

}