package com.github.killo200.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AmmoInHelicopter {
    int capacityHelicopter;
    List<Ammo> ammoNeed = new ArrayList<>();
    List<Ammo> ammoGet = new ArrayList<>();
    int[][] matrix;

    public static void main(String[] args) {
        AmmoInHelicopter aH = new AmmoInHelicopter();
        aH.readConditions();
        aH.matrixAmmo();
        aH.showAnswer();
    }


    public void showAnswer() {
        Map<Ammo, Integer> map = new TreeMap<>();
        for (Ammo ammo : ammoGet) {
            map.put(ammo, map.getOrDefault(ammo, 0) + 1);
        }

        for(Map.Entry<Ammo, Integer> pair : map.entrySet()) {
            System.out.println(pair.getValue() + " x " + pair.getKey().getName());
        }

    }


    public void matrixAmmo() {
        matrix = new int[ammoNeed.size() + 1][ capacityHelicopter + 1];
        for(int i = 0; i < ammoNeed.size() + 1; i++) {
            for (int j = 0; j < capacityHelicopter + 1; j++) {
                if (i == 0 || j == 0) { //нулевую строку и столбец заполняем нулями
                    matrix[i][j] = 0;
                } else if (i == 1) {

                    //заполняем первую строку - предмет кладется или нет в зависимости от веса
                    matrix[1][j] = ammoNeed.get(i).getWeight() <= j ? ammoNeed.get(i).getValue() : 0;
                } else {
                    if (ammoNeed.get(i - 1).getWeight() > j) //если очередной предмет не влезает в рюкзак,
                        matrix[i][j] = matrix[i - 1][j];    //записываем предыдущий максимум
                    else {
                        //рассчитаем ценность очередного предмета + максимальную цену для (максимально возможный для рюкзака вес − вес предмета)
                        int newValue = ammoNeed.get(i - 1).getValue() + matrix[i - 1][j - ammoNeed.get(i - 1).getWeight()];
                        if (matrix[i - 1][j] > newValue) //если предыдущий максимум больше
                            matrix[i][j] = matrix[i - 1][j]; //запишем его
                        else {
                            //иначе фиксируем новый максимум
                            matrix[i][j] = newValue;
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }

        findAns(ammoNeed.size(), capacityHelicopter);
    }

    public void readConditions() {
        File path = new File("src/main/java/com/github/killo200/task3/conditions task3.txt");
        List<String> conditions = new ArrayList<>();

        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                conditions.add(scanner.nextLine());
            }
            parseConditions(conditions);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    private void parseConditions(List<String> conditions) {
        for (int i = 0; i < conditions.size(); i++) {
            if (i == 0) {
                capacityHelicopter = Integer.parseInt(conditions.get(i));
            } else {
                String[] splitLineAmmo = conditions.get(i).split(" ");
                for (int j = 0; j < Integer.parseInt(splitLineAmmo[0]); j++) {
                    ammoNeed.add(new Ammo(
                            splitLineAmmo[2],
                            Integer.parseInt(splitLineAmmo[3]),
                            Integer.parseInt(splitLineAmmo[4]),
                            Integer.parseInt(splitLineAmmo[0])));
                }
            }
        }
    }

    private List<Ammo> findAns(int k, int s) {

        if (matrix[k][s] == 0) {
            return ammoGet;
        }
        if(matrix[k-1][s] == matrix[k][s]) {
            findAns(k - 1, s);
        } else {
            findAns(k-1, s - ammoNeed.get(k-1).getWeight());
            ammoGet.add(ammoNeed.get(k-1));
        }
        return ammoGet;
    }
}

class Ammo  implements Comparable<Ammo>{
    private String name;
    private int weight;
    private int value;
    private Integer count;

    public Ammo(String name, int weight, int value, int count) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Ammo{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ammo ammo = (Ammo) o;
        return weight == ammo.weight && value == ammo.value && Objects.equals(name, ammo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, value);
    }

    @Override
    public int compareTo(Ammo o) {
        return name.compareTo(o.getName());
    }
}
