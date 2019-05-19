package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.FocusModel;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem btnSave;

    @FXML
    private MenuItem btnQuit;

    @FXML
    private ListView<String> memberListView;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private ImageView imgBlockM;


    public void initialize(){
        ObservableList<String> names = FXCollections.observableArrayList(
                "Carter Mintey", "Julia Moseman", "Keenan Perera", "Jackson Hillmann",
                "Austin Duffy", "Alec Johnson", "Dylan Stobart", "Vannesa Robbins", "Nathan Noma"
        );
        memberListView.setItems(names);
    }

    @FXML
    void quitApp(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void viewProfile(ActionEvent event) throws IOException{
        if(event.getSource() == btnProfile){
            Stage memberStage = new Stage();
            // get an instance of the loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/memberView.fxml"));

            // get the focused member from the list
            FocusModel<String> fModel = memberListView.getFocusModel();
            String member = fModel.getFocusedItem();

            // load the scene and initialize the controller data
            memberStage.setScene(new Scene(loader.load()));
            MemberController memberController = loader.getController();
            memberController.initData(member, "2", "2", "43, 22");

            // set the title and show the stage
            memberStage.setTitle(String.format("%s's Details", member));
            memberStage.show();
        }
    }

}
