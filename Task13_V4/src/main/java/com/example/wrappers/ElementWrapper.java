package com.example.wrappers;

import org.openqa.selenium.WebElement;

public class ElementWrapper {
    private WebElement clickableElement; // Елемент, який можна клікнути (наприклад, label)
    private WebElement stateElement;     // Елемент, стан якого перевіряється (наприклад, input)
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

    // Для радіо-кнопок deselect не підтримується; тільки логування.
    public void deselect() {
        System.out.println("Deselect action is not supported for radio button " + name + ".");
    }

    // Перевірка стану вибору із логуванням
    public boolean isSelected() {
        boolean selected = stateElement.isSelected();
        System.out.println("Radio button " + name + " selected: " + selected);
        return selected;
    }
}
