package com.github.killo200.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CandyGame {
    int vasyaCount = 0;
    int[] condition = readConditions();
    int candyCount = condition[0];
    int maxTake = condition[1];
    int[] players = {0, 0};

    public static void main(String[] args) {
        CandyGame candyGame = new CandyGame();
        candyGame.readConditions();
        int candyVasyaEat = candyGame.game();
        System.out.println("В данной игре Вася смог съесть: " + candyVasyaEat + " конфет(ы)");
    }

    public int game() {
        int nextPlayerStart = 0;

        while (candyCount != 0) {
            if (candyCount != condition[0]) {
                players[0] = 0;
                players[1] = 0;

            }
            nextPlayerStart = round(nextPlayerStart);
            candyCount = players[nextPlayerStart];
            if(nextPlayerStart == 1) {
                vasyaCount += players[0];
            }
        }

        return vasyaCount;
    }

    private int round(int playerStart) {
        int indexPlayer = playerStart;
        int step = 0;
        while (candyCount != 0) {
            if (step != 0) {
                indexPlayer = (indexPlayer + 1) % 2;
            }
            if (candyCount - maxTake > maxTake) {
                candyCount -= maxTake;
                players[indexPlayer] += maxTake;
                step++;
            } else if (candyCount > maxTake && candyCount % maxTake != 0) {
                candyCount -= maxTake;
                players[indexPlayer] += maxTake;
                step++;
            } else if (candyCount > maxTake && candyCount % maxTake == 0) {
                if (maxTake - 1 != 0) {
                    candyCount -= (maxTake - 1);
                    players[indexPlayer] += (maxTake - 1);
                } else {
                    candyCount -= maxTake;
                    players[indexPlayer] += maxTake;
                }
                step++;
            } else {
                players[indexPlayer] += (candyCount);
                candyCount -= candyCount;
                step++;
            }

        }
        return (indexPlayer + 1) % 2;
    }

    public int[] readConditions() {
        File path = new File("src/main/java/com/github/killo200/task2/conditions task2.txt");
        int[] conditions = new int[2];
        int i = 0;

        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextInt()) {
                conditions[i++] = scanner.nextInt();
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Файл не найден или условие задачи не верно");
        }

        return conditions;
    }
}
