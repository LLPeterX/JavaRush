package com.javarush.task.task37.task3713;

import com.javarush.task.task37.task3713.space.crew.AbstractCrewMember;
import com.javarush.task.task37.task3713.space.SpaceShip;

/* 
Chain of Responsibility
*/
public class Solution {
    public static void main(String[] args) {
        SpaceShip spaceShip = new SpaceShip(); // тут строим цепочку наследования ответственности от юнги до капитана
        AbstractCrewMember crewMember = spaceShip.getCrewChain();

        crewMember.handleRequest(AbstractCrewMember.CompetencyLevel.EXPERT, "ATTACK");
        System.out.println("-----------------------------------------");
        crewMember.handleRequest(AbstractCrewMember.CompetencyLevel.NOVICE, "WASH THE FLOOR");
        System.out.println("-----------------------------------------");
        crewMember.handleRequest(AbstractCrewMember.CompetencyLevel.INTERMEDIATE, "CHECK ENGINE");
        System.out.println("-----------------------------------------");
        crewMember.handleRequest(AbstractCrewMember.CompetencyLevel.ADVANCED, "SET ROUTE");
    }
}
