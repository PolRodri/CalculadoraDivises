package com.example.calculadoradivises;

public class Global {
    private static Global mInstance= null;

    public double valorConversor = 0.91;

    protected Global(){}

    public static synchronized Global getInstance() {
        if(null == mInstance){
            mInstance = new Global();
        }
        return mInstance;
    }
}
