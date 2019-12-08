package com.jrula.sample.data;

import com.jrula.sample.Element;
import javafx.collections.ObservableList;


public interface LoadData {
    ObservableList<Element> loadData(int number);
}
