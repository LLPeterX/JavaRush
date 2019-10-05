package com.javarush.task.task26.task2613;

public enum Operation {
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        for(Operation o : Operation.values())
            if(o.ordinal() == i-1)
                return o;
        throw new IllegalArgumentException();
    }
}
