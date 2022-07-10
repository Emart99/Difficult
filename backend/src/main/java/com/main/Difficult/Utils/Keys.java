package com.main.Difficult.Utils;

public final class Keys {
    private static Keys instance = null;
    private Long contador = 0L;

    private Keys(){}
    public synchronized static Keys getInstance(){
        if(instance == null){
            instance = new Keys();
        }
        return instance;
    }

    public Long getContador(){
        contador +=1;
        return this.contador;
    }
}
