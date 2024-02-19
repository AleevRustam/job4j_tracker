package ru.job4j.tracker.input;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.assertj.core.api.Assertions.assertThat;

class ValidateInputTest {

    @Test
    void whenInvalidInput() {
        Output output = new StubOutput();
        Input in = new MockInput(
                new String[]{"one", "1"}
        );
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenValidInput() {
        Output output = new StubOutput();
        Input in = new MockInput(new String[]{"2"});
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(2);
    }

    @Test
    void whenMultiplyValidInput() {
        Output output = new StubOutput();
        Input in1 = new MockInput(new String[]{"1"});
        ValidateInput input1 = new ValidateInput(output, in1);
        int selected1 = input1.askInt("Enter menu:");
        assertThat(selected1).isEqualTo(1);
        Input in2 = new MockInput(new String[]{"2"});
        ValidateInput input2 = new ValidateInput(output, in2);
        int selected2 = input2.askInt("Enter menu:");
        assertThat(selected2).isEqualTo(2);
        Input in3 = new MockInput(new String[]{"3"});
        ValidateInput input3 = new ValidateInput(output, in3);
        int selected3 = input3.askInt("Enter menu:");
        assertThat(selected3).isEqualTo(3);

    }

    @Test
    void whenNegativeNumberValidInput() {
        Output output = new StubOutput();
        Input in = new MockInput(new String[]{"-1"});
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(-1);
    }
}