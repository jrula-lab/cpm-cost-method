package com.jrula.sample.data;

import com.jrula.sample.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class MockData implements LoadData {

    private static Map<Integer, ObservableList<Element>> dataSet = new HashMap<>();
    static {
        ObservableList<Element> elements = FXCollections.observableArrayList();
        elements.add(new Element("1-2", "8", "6", "280", "400", "60"));
        elements.add(new Element("1-4", "10", "5", "100", "150", "10"));
        elements.add(new Element("2-3", "6", "4", "30", "400", "50"));
        elements.add(new Element("3-6", "12", "10", "260", "300", "20"));
        elements.add(new Element("4-5", "15", "15", "150", "150", "-"));
        elements.add(new Element("5-6", "10", "2", "200", "360", "20"));
        dataSet.put(1, elements);
        ObservableList<Element> elements1 = FXCollections.observableArrayList();
        elements1.add(new Element("1-2", "6", "4", "10", "16", "3"));
        elements1.add(new Element("2-3", "4", "3", "13", "15", "2"));
        elements1.add(new Element("3-4", "5", "5", "12", "12", "-"));
        elements1.add(new Element("3-5", "8", "6", "14", "16", "1"));
        elements1.add(new Element("4-6", "10", "10", "6", "6", "-"));
        elements1.add(new Element("5-7", "6", "6", "11", "11", "-"));
        elements1.add(new Element("6-7", "3", "2", "4", "8", "4"));
        elements1.add(new Element("7-8", "2", "2", "3", "3", "-"));
        dataSet.put(2, elements1);
        ObservableList<Element> elements2 = FXCollections.observableArrayList();
        elements2.add(new Element("0-1", "5", "5", "30", "30", "-"));
        elements2.add(new Element("0-2", "8", "5", "44", "50", "2"));
        elements2.add(new Element("0-3", "7", "5", "30", "35", "2.5"));
        elements2.add(new Element("1-3", "6", "4", "25", "30", "2.5"));
        elements2.add(new Element("2-3", "8", "4", "35", "40", "1.25"));
        elements2.add(new Element("2-5", "10", "8", "44", "50", "3"));
        elements2.add(new Element("3-4", "5", "4", "10", "12", "2"));
        elements2.add(new Element("3-5", "10", "7", "24", "28", "1.33"));
        elements2.add(new Element("4-5", "6", "4", "20", "26", "3"));
        dataSet.put(3, elements2);
    }
    @Override
    public ObservableList<Element> loadData(int number) {
        return dataSet.get(number);
    }
}
