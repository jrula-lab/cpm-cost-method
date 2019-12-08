package com.jrula.sample;

import com.jrula.sample.data.DataFactory;
import com.jrula.sample.data.LoadData;
import com.jrula.sample.model.DesignateCPM;
import com.jrula.sample.model.ModelFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Element> tableView;
    @FXML
    private TableColumn<Element, String> i_j;
    @FXML
    private TableColumn<Element, String> tn;
    @FXML
    private TableColumn<Element, String> tgr;
    @FXML
    private TableColumn<Element, String> kn;
    @FXML
    private TableColumn<Element, String> kgr;
    @FXML
    private TableColumn<Element, String> S;
    @FXML
    private TextField textTgr;
    @FXML
    private TextField textTn;
    @FXML
    private TextField textKn;
    @FXML
    private TextField textKgr;
    @FXML
    private TextField textId;
    @FXML
    private Label critical_path;
    @FXML
    private Label cpm_method;

    private DesignateCPM designateCPM = ModelFactory.createModel(Element.class);

    private LoadData loadData = DataFactory.createDataSet(Element.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        i_j.setCellValueFactory(data->data.getValue().i_jProperty());
        tn.setCellValueFactory(data->data.getValue().tnProperty());
        tgr.setCellValueFactory(data->data.getValue().tgrProperty());
        kn.setCellValueFactory(data->data.getValue().knProperty());
        kgr.setCellValueFactory(data -> data.getValue().kgrProperty());
        S.setCellValueFactory(data -> data.getValue().sProperty());
    }

    @FXML
    private void zestaw1_action(ActionEvent actionEvent){
        actionEvent.consume();
        ObservableList<Element> elements = loadData.loadData(1);
        tableView.setItems(elements);
    }

    @FXML
    private void zestaw2_action(ActionEvent actionEvent){
        actionEvent.consume();
        ObservableList<Element> elements = loadData.loadData(2);
        tableView.setItems(elements);
    }
    @FXML
    private void zestaw3_action(ActionEvent actionEvent){
        actionEvent.consume();
        ObservableList<Element> elements = loadData.loadData(3);
        tableView.setItems(elements);
    }

    @FXML
    private void add_element(ActionEvent actionEvent){
        actionEvent.consume();
        Element element = new Element();
        System.out.println(textId.getText());
        element.setI_j(textId.getText());
        element.setTn(textTn.getText());
        element.setTgr(textTgr.getText());
        element.setKn(textKn.getText());
        element.setKgr(textKgr.getText());
        System.out.println(calculateS(element));
        element.setS(calculateS(element));
        tableView.getItems().add(element);
        clearTextFields();
    }

    @FXML
    private void deleteSelected(ActionEvent actionEvent){
        actionEvent.consume();
        ObservableList<Element> observableList = tableView.getItems();
        int index= tableView.getSelectionModel().getSelectedIndex();
        observableList.remove(index);
        tableView.setItems(observableList);
    }

    @FXML
    private void deleteAllItems(ActionEvent actionEvent){
        actionEvent.consume();
        ObservableList<Element> elements = tableView.getItems();
        elements.clear();
        tableView.setItems(elements);
    }

    @FXML
    private void designateCriticalPath(ActionEvent actionEvent){
        actionEvent.consume();
        designateCPM.loadDataFromController(tableView.getItems());
        String criticalPath = designateCPM.designateCriticalPath();
        critical_path.setText(criticalPath);
    }

    @FXML
    private void cpmCostMethod(ActionEvent actionEvent){
        actionEvent.consume();
        String criticalPathCost = designateCPM.designateCPMMethodCost();
        cpm_method.setText(criticalPathCost);
    }

    private String calculateS(Element element){
        Double Kgr = Double.parseDouble(element.getKgr());
        Double Kn = Double.parseDouble(element.getKn());
        Double tgr = Double.parseDouble(element.getTgr());
        Double tn = Double.parseDouble(element.getTn());
        if(tn.equals(tgr)){
            return "-";
        }
        return Double.toString((Kgr - Kn)/(tn - tgr));
    }

    private void clearTextFields(){
        textId.clear();
        textTn.clear();
        textTgr.clear();
        textKn.clear();
        textKgr.clear();
    }
}
