package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {

    @Test
    void whenAllKeysPresentThenCorrectResult() {
        GeneratorImpl generator = new GeneratorImpl();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo("I am a Petr Arsentev, Who are you?");
    }

    @Test
    void whenKeyMissingInMapThenThrowException() {
        GeneratorImpl generator = new GeneratorImpl();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        String template = "I am a ${name}, Who are ${subject}?";
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenExtraKeyInMapThenThrowException() {
        GeneratorImpl generator = new GeneratorImpl();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("extra", "extraValue");
        String template = "I am a ${name}, Who are ${subject}?";
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

}