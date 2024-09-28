package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FoolTest {
    @Test
    void testGameResult() {
        Fool game = new Fool();
        assertThat(game.getFizzBuzzResult(1)).isEqualTo("1");
        assertThat(game.getFizzBuzzResult(2)).isEqualTo("2");

        assertThat(game.getFizzBuzzResult(3)).isEqualTo("Fizz");
        assertThat(game.getFizzBuzzResult(6)).isEqualTo("Fizz");

        assertThat(game.getFizzBuzzResult(5)).isEqualTo("Buzz");
        assertThat(game.getFizzBuzzResult(10)).isEqualTo("Buzz");

        assertThat(game.getFizzBuzzResult(15)).isEqualTo("FizzBuzz");
        assertThat(game.getFizzBuzzResult(30)).isEqualTo("FizzBuzz");
    }

    @Test
    void testAnswerCorrect() {
        Fool game = new Fool();

        game.setCurrentNumber(1);
        assertThat(game.isAnswerCorrect("1")).isTrue();

        game.setCurrentNumber(3);
        assertThat(game.isAnswerCorrect("Fizz")).isTrue();

        game.setCurrentNumber(5);
        assertThat(game.isAnswerCorrect("Buzz")).isTrue();

        game.setCurrentNumber(15);
        assertThat(game.isAnswerCorrect("FizzBuzz")).isTrue();
    }

    @Test
    void testAnswerIncorrect() {
        Fool game = new Fool();

        game.setCurrentNumber(2);
        assertThat(game.isAnswerCorrect("Fizz")).isFalse();

        game.setCurrentNumber(6);
        assertThat(game.isAnswerCorrect("Buzz")).isFalse();

        game.setCurrentNumber(10);
        assertThat(game.isAnswerCorrect("Fizz")).isFalse();
    }
}