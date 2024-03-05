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
            double avg = (double) sum / subjects.size();
            result.add(new Label(pupil.name(), avg));
        }
        return result;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        Map<String, Integer> subjectsMapAllPupils = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            List<Subject> subjectsListOnePupil = pupil.subjects();
            for (Subject subject : subjectsListOnePupil) {
                String key = subject.name();
                Integer value = subjectsMapAllPupils.getOrDefault(key, 0) + subject.score();
                subjectsMapAllPupils.put(key, value);
            }
        }
        for (Map.Entry<String, Integer> entry : subjectsMapAllPupils.entrySet()) {
            String key = entry.getKey();
            double value = (double) entry.getValue() / pupils.size();
            result.add(new Label(key, value));
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
                String key = subject.name();
                Integer value = subjectsMapAllPupils.getOrDefault(key, 0) + subject.score();
                subjectsMapAllPupils.put(key, value);
            }
        }
        for (Map.Entry<String, Integer> entry : subjectsMapAllPupils.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            result.add(new Label(key, value));
        }
        result.sort(Comparator.naturalOrder());
        return result.get(result.size() - 1);
    }
}
