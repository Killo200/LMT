package com.github.killo200.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnderAttack {

    List<Integer> conditions;

    public static void main(String[] args) {
        UnderAttack uA = new UnderAttack();
        uA.answer();
    }


    public List<Long> answer() {
        readConditions();
        List<Long> answer = new ArrayList<>();
        try {
            if (conditions.size() % 2 == 0) {
                List<Integer> pointObservers = conditions.subList(0, conditions.size() / 2);
                List<Integer> azimutObservers = conditions.subList(conditions.size() / 2, conditions.size());
                for (int i = 0; i < pointObservers.size(); i += 2) {
                    if (azimutObservers.get(i) == 90 || azimutObservers.get(i) == 180) {
                        throw new ArithmeticException("ERROR");
                    }

                    double c = Math.abs(pointObservers.get(i)) / Math.sin(Math.toRadians(azimutObservers.get(i)));
                    long toDestination = Math.round(c);

                    double a = toDestination * Math.cos(Math.toRadians(azimutObservers.get(i)));
                    long pointY = Math.round(a + pointObservers.get(i + 1));
                    long pointX = Math.abs(pointObservers.get(i)) - Math.abs(pointObservers.get(i));
                    //System.out.println(toDestination);
                    answer.add(pointX);
                    answer.add(pointY);
                    System.out.println(pointX + " " + pointY);
                }
            } else {
                System.out.println("Указаны не верные условия задачи.");
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return answer;
    }

    public void readConditions() {
        File path = new File("src/main/java/com/github/killo200/task1/conditions task1.txt");
        conditions = new ArrayList<>();

        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextInt()) {
                conditions.add(scanner.nextInt());
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Файл не найден или условие задачи не верно");
        }
    }
}
