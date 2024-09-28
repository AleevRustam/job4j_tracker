package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
     private int currentNumber;
    private Scanner input;

    public Fool() {
        this.currentNumber = 1;
        this.input = new Scanner(System.in);
    }

    public void setCurrentNumber(int number) {
        this.currentNumber = number;
    }

    public void start() {
        System.out.println("Игра FizzBuzz");
        while (currentNumber <= 100) {
            printExpectedOutput();
            currentNumber++;

            String userAnswer = input.nextLine();

            if (!isAnswerCorrect(userAnswer)) {
                System.out.println("Ошибка. Начинай снова.");
                currentNumber = 1;
                continue;
            }
            currentNumber++;
        }
    }

    private void printExpectedOutput() {
        String result = getFizzBuzzResult(currentNumber);
        System.out.println(result);
    }

    public boolean isAnswerCorrect(String answer) {
        String correctAnswer = getFizzBuzzResult(currentNumber);
        return correctAnswer.equals(answer);
    }

    public String getFizzBuzzResult(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }

    public static void main(String[] args) {
        Fool game = new Fool();
        game.start();
    }
}
