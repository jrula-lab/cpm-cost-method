package com.jrula.sample.model;


public class ModelFactory {

    public static DesignateCPM createModel(Class<?> clazz){
        DesignateCPM designateCPM = null;
        if(clazz.getName().equals("com.jrula.sample.Element")){
            designateCPM = new DesignateCPMImp();
        }
        return designateCPM;
    }
}
