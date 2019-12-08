package com.jrula.sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Element {
    private StringProperty i_j;
    private StringProperty tn;
    private StringProperty tgr;
    private StringProperty Kn;
    private StringProperty Kgr;
    private StringProperty S;

    public Element(){
        this.i_j= new SimpleStringProperty();
        this.tn = new SimpleStringProperty();
        this.tgr = new SimpleStringProperty();
        this.Kn = new SimpleStringProperty();
        this.Kgr = new SimpleStringProperty();
        this.S = new SimpleStringProperty();
    }

    public Element(String i_j, String tn, String tgr, String kn, String kgr, String s) {
        this.i_j= new SimpleStringProperty(i_j);
        this.tn = new SimpleStringProperty(tn);
        this.tgr = new SimpleStringProperty(tgr);
        this.Kn = new SimpleStringProperty(kn);
        this.Kgr = new SimpleStringProperty(kgr);
        this.S = new SimpleStringProperty(s);
    }

    public String getI_j() {
        return i_j.get();
    }

    public StringProperty i_jProperty() {
        return i_j;
    }

    public void setI_j(String i_j) {
        this.i_j.set(i_j);
    }

    public String getTn() {
        return tn.get();
    }

    public StringProperty tnProperty() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn.set(tn);
    }

    public String getTgr() {
        return tgr.get();
    }

    public StringProperty tgrProperty() {
        return tgr;
    }

    public void setTgr(String tgr) {
        this.tgr.set(tgr);
    }

    public String getKn() {
        return Kn.get();
    }

    public StringProperty knProperty() {
        return Kn;
    }

    public void setKn(String kn) {
        this.Kn.set(kn);
    }

    public String getKgr() {
        return Kgr.get();
    }

    public StringProperty kgrProperty() {
        return Kgr;
    }

    public void setKgr(String kgr) {
        this.Kgr.set(kgr);
    }

    public String getS() {
        return S.get();
    }

    public StringProperty sProperty() {
        return S;
    }

    public void setS(String s) {
        this.S.set(s);
    }
}
