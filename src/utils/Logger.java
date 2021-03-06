package utils;

import java.util.Date;

public class Logger {

    private  String mTag = "";

    public Logger(String tag){
        mTag = tag;
    }

    private static String getTime(){
        Date date = new Date(System.currentTimeMillis());
        return date.toLocaleString();
    }

    public void d(String msg){
        System.out.println(getTime() + "? D/" + mTag + ": " + msg);
    }

    public void print(Object msg){
        System.out.print(String.valueOf(msg));
    }

    public void println(Object msg){
        System.out.println(String.valueOf(msg));
    }

    public void println(){
        println("");
    }

    public void printArray(int[] objects){
        if (objects != null){
            for (Object o : objects){
                print(o);
                print("\t");
            }
            println("");
        }
    }

    public void printArray(int[][] ints){
        if (ints != null){
            for (int[] o: ints){
                printArray(o);
            }
        }
    }

}
