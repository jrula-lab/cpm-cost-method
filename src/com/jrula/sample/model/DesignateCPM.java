package com.jrula.sample.model;

import com.jrula.sample.Element;
import javafx.collections.ObservableList;

public interface DesignateCPM {
    String designateCriticalPath();
    String designateCPMMethodCost();
    void loadDataFromController(ObservableList<Element> observableList);
}
