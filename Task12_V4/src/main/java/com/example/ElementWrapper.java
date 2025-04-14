package com.example;

import org.openqa.selenium.WebElement;

public class ElementWrapper {
    private WebElement clickableElement; // Елемент, який викликає клік (label)
    private WebElement stateElement;     // Елемент для перевірки стану (input)
    private String name;

    public ElementWrapper(WebElement clickableElement, WebElement stateElement, String name) {
        this.clickableElement = clickableElement;
        this.stateElement = stateElement;
        this.name = name;
    }

    // Метод для вибору (select) радіо-кнопки з логуванням
    public void select() {
        if (!isSelected()) {
            clickableElement.click();
            System.out.println("Selected radio button: " + name);
        } else {
            System.out.println("Radio button " + name + " is already selected.");
        }
    }

    // Метод для зняття вибору (не підтримується для радіо-кнопок)
    public void deselect() {
        System.out.println("Deselect action is not supported for radio button " + name + ".");
    }

    // Метод для перевірки, чи вибрана радіо-кнопка, із логуванням
    public boolean isSelected() {
        boolean selected = stateElement.isSelected();
        System.out.println("Radio button " + name + " selected: " + selected);
        return selected;
    }
}
