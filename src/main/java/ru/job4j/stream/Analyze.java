package ru.job4j.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        OptionalDouble average = stream
                .flatMap(pupil -> pupil.subjects().stream())
                .mapToInt(Subject::score)
                .average();

        double result = average.getAsDouble();
        return result;
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        List<Tuple> result = stream
                .map(pupil -> new Tuple(pupil.name(), pupil.subjects().stream()
                        .mapToInt(Subject::score)
                        .average()
                        .getAsDouble()))
                .collect(Collectors.toList());
        return result;
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .flatMap(pupil -> pupil.subjects().stream())
                .collect(Collectors
                        .groupingBy(subject -> subject.name(), LinkedHashMap::new, Collectors.averagingDouble(Subject::score)))
                .entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        Tuple result = stream
                .map(pupil -> new Tuple(pupil.name(), pupil.subjects().stream()
                        .mapToInt(Subject::score)
                        .sum())).max(Comparator.comparing(Tuple::score)).orElse(null);
        return result;
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        Tuple result = stream
                .flatMap(pupil -> pupil.subjects().stream())
                .collect(Collectors
                        .groupingBy(subject -> subject.name(),
                                LinkedHashMap::new,
                                Collectors.summingDouble(Subject::score)))
                .entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .max(Comparator.comparing(Tuple::score)).orElse(null);

        return result;
    }
}
