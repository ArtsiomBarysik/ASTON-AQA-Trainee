package org.example;
/**
 * Практическое задание
 * Создайте 2 ветки: Lesson_7_junit_5 и Lesson_7_testng.
 * Напишите программу, позволяющую вычислить факториал числа.
 * Эта программа должна быть в каждой ветке.
 * В ветке Lesson_7 junit_5 напишите юнит-тесты для этой программы, используя Junit 5.
 * В ветке Lesson_7_testng напишите юнит-тесты, используя TestNG.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(getFactorial(31));

    }
    public static int getFactorial(int number) {
        int result = 1;
        if (number < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        } else if (number > 31) {
            throw new IllegalArgumentException("Число не может быть больше 31");
        }
        for (int i = 1; i<= number; i++) {
            result *= i;
        }
        return result;
    }
}