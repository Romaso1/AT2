package com.example;

public class Task2 {

    // Повертає простий масив цілих чисел
    public int[] getArray() {
        int[] arr = {1, 2, 3, 4, 5};
        return arr;
    }

    // Використовує for для формування рядкового представлення масиву
    public String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    // Використовує for для обчислення суми елементів масиву
    public int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
