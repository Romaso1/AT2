package com.example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Task2Test {

    Task2 task2 = new Task2();

    // DataProvider для тестування arrayToString
    @DataProvider(name = "arrayToStringData")
    public Object[][] arrayToStringData() {
        return new Object[][]{
                { new int[]{1, 2, 3}, "1, 2, 3" },
                { new int[]{}, "" },
                { new int[]{10, 20}, "10, 20" }
        };
    }

    @Test(dataProvider = "arrayToStringData")
    public void testArrayToString(int[] input, String expected) {
        String result = task2.arrayToString(input);
        Assert.assertEquals(result, expected, "Рядкове представлення масиву не співпадає з очікуваним!");
    }

    // DataProvider для тестування суми елементів
    @DataProvider(name = "sumArrayData")
    public Object[][] sumArrayData() {
        return new Object[][]{
                { new int[]{1, 2, 3}, 6 },
                { new int[]{}, 0 },
                { new int[]{10, 20, 30}, 60 }
        };
    }

    @Test(dataProvider = "sumArrayData")
    public void testSumArray(int[] input, int expectedSum) {
        int result = task2.sumArray(input);
        Assert.assertEquals(result, expectedSum, "Обчислена сума елементів не співпадає з очікуваною!");
    }

    // Тест із параметрами, завантаженими з testng.xml
    // Очікується, що метод getArray поверне масив з певною довжиною
    @Test
    @Parameters("expectedLength")
    public void testGetArray(int expectedLength) {
        int[] array = task2.getArray();
        Assert.assertEquals(array.length, expectedLength, "Довжина масиву не співпадає з очікуваною!");
    }
}
