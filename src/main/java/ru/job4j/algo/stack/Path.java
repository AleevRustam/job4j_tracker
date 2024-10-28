package ru.job4j.algo.stack;

import java.util.Stack;

public class Path {
    public String simplify(String path) {
        var stack = new Stack<>();
        var components = path.split("/");
        for (String component : components) {
            if (component.equals("..")) {
                stack.pop();
            } else if (!component.isEmpty() && !component.equals(".")) {
                stack.push(component);
            }
        }
        var result = new StringBuilder();
        for (Object dir : stack) {
            result.append("/").append(dir);
        }

        return !result.isEmpty() ? result.toString() : "/";
    }
}
