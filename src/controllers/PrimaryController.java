package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Marcher;
import utils.DataHandler;
import utils.LegacyData;
import utils.MarcherCellFactory;

import java.io.File;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem btnNew;

    @FXML
    private MenuItem btnOpen;

    @FXML
    private MenuItem btnSave;

    @FXML
    private MenuItem btnSaveAs;

    @FXML
    private MenuItem btnQuit;

    @FXML
    private ListView<Marcher> memberListView;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private ImageView imgBlockM;

    private boolean isSaved;
    private DataHandler<Marcher> data;
    private LegacyData old_data;
    private File editingFile;


    public void initialize(){
        //this.isSaved = true;
        //old_data = new LegacyData();
        //old_data.importData("/home/cmintey/pregame-helper/src/resources/trumpets.csv");
        data = new DataHandler<>();
        //data.importData("/home/cmintey/pregame-helper/src/resources/trumpets.dat");
        //data.importData(old_data.getData());
        //data.exportData("/home/cmintey/pregame-helper/src/resources/trumpets.dat");

        // Change cellFactory so we can see the names of the Marcher objects
        memberListView.setCellFactory(new MarcherCellFactory());
        memberListView.setItems(data.getData());
    }


    @FXML
    void createNew(ActionEvent event) {
        if (event.getSource() == btnNew){
            //TODO: make add profile button
        }

    }

    @FXML
    void open(ActionEvent event) {
        if (event.getSource() == btnOpen){
            // create new stage
            Stage fileStage = new Stage();

            // set up file chooser
            FileChooser fc = new FileChooser();
            fc.setTitle("Open a file");
            fc.setInitialDirectory(new File("/home/cmintey/pregame-helper/src/resources"));
            File selectedFile = fc.showOpenDialog(fileStage);

            if (selectedFile != null){
                data.importData(selectedFile);
                editingFile = selectedFile;
                isSaved = true;
            }
        }
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
            data.exportData(this.editingFile);
        }
    }

    @FXML
    void saveAs(ActionEvent event) {
         if (event.getSource() == btnSaveAs){
             // create new stage
             Stage fileStage = new Stage();

             // set up file chooser
             FileChooser fc = new FileChooser();
             fc.setTitle("Save file");
             fc.setInitialFileName(editingFile.getName());
             fc.setInitialDirectory(editingFile.getParentFile());
             File savedFile = fc.showSaveDialog(fileStage);

             if (savedFile != null){
                 data.exportData(savedFile);
             }
        }
    }

    @FXML
    void perfRightClick(ActionEvent event) {
        if(event.getSource() == btnProfile){
            viewProfile();
        }
    }

    @FXML
    void perfClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY &&
        event.getClickCount() == 2){
            viewProfile();
        }
    }

    void viewProfile() {
        Stage memberStage = new Stage();
        // get an instance of the loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/memberView.fxml"));

        // get the focused member from the list
        Marcher member = memberListView.getFocusModel().getFocusedItem();

        // load the scene and initialize the controller data
        try {
            memberStage.setScene(new Scene(loader.load()));
            MemberController memberController = loader.getController();
            memberController.initData(member);

            // set the title and show the stage
            memberStage.setTitle(String.format("%s's Details", member));
            memberStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
