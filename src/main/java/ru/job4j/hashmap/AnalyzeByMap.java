package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sum = 0;
        List<Label> labels = averageScoreByPupil(pupils);
        for (Label label : labels) {
            sum += label.score();
        }
        return sum / labels.size();
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int sum = 0;
            List<Subject> subjects = pupil.subjects();
            for (Subject subject : subjects) {
                sum += subject.score();
            }
            result.add(new Label(pupil.name(), (double) sum / subjects.size()));
        }
        return result;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        Map<String, Integer> subjectsMapAllPupils = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            List<Subject> subjectsListOnePupil = pupil.subjects();
            for (Subject subject : subjectsListOnePupil) {
                subjectsMapAllPupils.put(subject.name(), subjectsMapAllPupils.getOrDefault(subject.name(), 0) + subject.score());
            }
        }
        for (Map.Entry<String, Integer> entry : subjectsMapAllPupils.entrySet()) {
            result.add(new Label(entry.getKey(), (double) entry.getValue() / pupils.size()));
        }
        return result;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int sum = 0;
            List<Subject> subjects = pupil.subjects();
            for (Subject subject : subjects) {
                sum += subject.score();
            }
            result.add(new Label(pupil.name(), sum));
        }
        result.sort(Comparator.naturalOrder());
        return result.get(result.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        Map<String, Integer> subjectsMapAllPupils = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            List<Subject> subjectsListOnePupil = pupil.subjects();
            for (Subject subject : subjectsListOnePupil) {
                subjectsMapAllPupils.put(subject.name(), subjectsMapAllPupils.getOrDefault(subject.name(), 0) + subject.score());
            }
        }
        for (Map.Entry<String, Integer> entry : subjectsMapAllPupils.entrySet()) {
            result.add(new Label(entry.getKey(), entry.getValue()));
        }
        result.sort(Comparator.naturalOrder());
        return result.get(result.size() - 1);
    }
}
