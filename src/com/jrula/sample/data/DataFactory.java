package com.jrula.sample.data;


public class DataFactory {
    public static LoadData createDataSet(Class<?> clazz){
        LoadData loadData = null;
        if(clazz.getName().equals("com.jrula.sample.Element")){
            loadData = new MockData();
        }
        return loadData;
    }
}
