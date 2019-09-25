package com.javarush.task.task22.task2201;

import javafx.beans.binding.StringBinding;

public class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        } else
            if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
                System.out.println(getFormattedStringForSecondThread(t, e, string));
            } else {
                System.out.println(getFormattedStringForOtherThread(t, e, string));
            }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string) {
        //return null;
//        StringBuilder sb = new StringBuilder();
//        sb.append(e.getClass().getSimpleName());
//        sb.append(" : ");
//        sb.append("java.lang.StringIndexOutOfBoundsException");
//        sb.append(": ");
//        sb.append("String index out of range: -1");
//        sb.append(" : ");
//        sb.append(t.getName());
//        return sb.toString();
        return String.format(string, e.getClass().getSimpleName(), e.getCause(), t.getName());
        //return "RuntimeException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : 3#";
         //       RuntimeException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : 3#
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("java.lang.StringIndexOutOfBoundsException: ");
//        sb.append("String index out of range: -1 : ");
//        sb.append(e.getClass().getSimpleName());
//        sb.append(" : ");
//        sb.append(t.getName());
//        return sb.toString();
        return String.format(string, e.getCause(), e.getClass().getSimpleName(), t.getName());
        //return "java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : StringForSecondThreadTooShortException : 2#";
        //        java.lang.StringIndexOutOfBoundsException: String index out of range: -1 :StringForSecondThreadTooShortException : 2#
        //return null;
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string) {
        return String.format(string,t.getName(),e.getClass().getSimpleName(),e.getCause());
//        return "1# : StringForFirstThreadTooShortException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1";
//                1# : StringForFirstThreadTooShortException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1
    }
}

