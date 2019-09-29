package com.javarush.task.task37.task3713.space.crew;

public abstract class AbstractCrewMember {
    public enum CompetencyLevel {
        NOVICE,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }

    protected CompetencyLevel competencyLevel;

    protected AbstractCrewMember nextCrewMember; // следущий по цепочке

    public void setNextCrewMember(AbstractCrewMember nextCrewMember) {
        this.nextCrewMember = nextCrewMember;
    }

    // этот метод подлежит переделать
    public void handleRequest(CompetencyLevel competencyLevel, String request) {

/* ---- оригниальный код, который надо заменить
        if (nextCrewMember.competencyLevel == CompetencyLevel.EXPERT) {
            doTheJob(request);
        } else if (nextCrewMember != null) {
            nextCrewMember.handleRequest(competencyLevel, request);
        }
 */
        if (this.competencyLevel == competencyLevel) {
            doTheJob(request);
        } else  if(nextCrewMember!=null) {
            nextCrewMember.handleRequest(competencyLevel,request);
        }
    }

    protected abstract void doTheJob(String request);
}
