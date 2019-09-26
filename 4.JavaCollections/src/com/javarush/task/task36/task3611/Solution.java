package com.javarush.task.task36.task3611;

import java.util.HashSet;
import java.util.Set;

/* 
Сколько у человека потенциальных друзей?
*/

public class Solution {
    private boolean[][] humanRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();

        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(4, 2);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]
    }

    // Твоя задача реализовать метод Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep),
    // который возвращает индексы людей, которые у тебя в друзьях уже есть и которых ты можешь знать.
    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {
        //напишите тут ваш код
        // используем рекурсию
        Set<Integer> result = new HashSet<>(); // tmp hash
        if (deep == 0) return result;
        for (int i = 0; i < humanRelationships.length; i++) {
            if (i < index) {
                // до указанного индекса
                if (humanRelationships[index][i]) { // друг
                    result.add(i);
                    result.addAll(getAllFriendsAndPotentialFriends(i, deep - 1));
                }
            } else {
                // после индекса
                if (humanRelationships[i][index]) {
                    result.add(i);
                    result.addAll(getAllFriendsAndPotentialFriends(i, deep - 1));
                }
            }
        }
        result.remove(index);
        return result;

    }

    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0 - я сам (по диагонали - я)
                {true, true},                                                           //1 - мои друзья
                {false, true, true},                                                    //2 - друзья моих друзей
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }
}