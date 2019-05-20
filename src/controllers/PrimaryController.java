package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Marcher;
import utils.DataHandler;
import utils.MarcherCellFactory;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem btnSave;

    @FXML
    private MenuItem btnQuit;

    @FXML
    private ListView<Marcher> memberListView;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private ImageView imgBlockM;

    private boolean isSaved;
    private DataHandler data;


    public void initialize(){
        this.isSaved = true;
        data = new DataHandler();
        data.importData("C:/Users/carte/projects/pregame-helper/src/resources/trumpets.csv");

        // ObservableList will detect changed and update the list accordingly
        ObservableList<Marcher> trumpets = FXCollections.observableArrayList(data.getData());

        // Change cellFactory so we can see the names of the Marcher objects
        memberListView.setCellFactory(new MarcherCellFactory());
        memberListView.setItems(trumpets);
    }

    @FXML
    void quitApp(ActionEvent event) {
        if (event.getSource() == btnQuit){
            if (isSaved){
                System.exit(0);
            }
            else{
                //TODO: Implement warning if user has not saved data
            }
        }
    }

    @FXML
    void save(ActionEvent event) {
        if (event.getSource() == btnSave){
            data.exportData("C:/Users/carte/projects/pregame-helper/src/resources/trumpets1.csv");
        }
    }

    @FXML
    void viewProfile(ActionEvent event) throws IOException{
        if(event.getSource() == btnProfile){
            Stage memberStage = new Stage();
            // get an instance of the loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/memberView.fxml"));

            // get the focused member from the list
            FocusModel<Marcher> fModel = memberListView.getFocusModel();
            Marcher member = fModel.getFocusedItem();

            // load the scene and initialize the controller data
            memberStage.setScene(new Scene(loader.load()));
            MemberController memberController = loader.getController();
            memberController.initData(member);

            // set the title and show the stage
            memberStage.setTitle(String.format("%s's Details", member));
            memberStage.show();
        }
    }

}
