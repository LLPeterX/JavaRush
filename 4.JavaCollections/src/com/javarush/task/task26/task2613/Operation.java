package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN, // д.б. 0
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        if(i ==0)
            throw  new IllegalArgumentException();
        for(Operation o : Operation.values())
            if(o.ordinal() == i)
                return o;
        throw new IllegalArgumentException();
    }
}
