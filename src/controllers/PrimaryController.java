package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private MenuItem btnMenuNewMarcher;

    @FXML
    private ListView<Marcher> memberListView;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private MenuItem btnNewMarcher;

    @FXML
    private MenuItem btnDeleteMarcher;

    private boolean isSaved;
    private DataHandler<Marcher> data;
    private LegacyData old_data;
    private File editingFile;


    public void initialize(){
        //this.isSaved = true;
        //old_data = new LegacyData();
        //old_data.importData("/home/cmintey/pregame-helper/src/resources/trumpets.csv");
        data = new DataHandler<>();
        data.importData(new File("/home/cmintey/pregame-helper/data/trumpets.dat"));
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
            //TODO: ask if they want to save before
            //TODO: reset list
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
            fc.setInitialDirectory(new File("/home/cmintey/pregame-helper/data"));
            fc.setInitialFileName("trumpets.dat");
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
    void btnAction(ActionEvent event) {
        if(event.getSource() == btnProfile){
            // get the focused member from the list
            viewProfile(memberListView.getFocusModel().getFocusedItem());
        }
        else if(event.getSource() == btnNewMarcher || event.getSource() == btnMenuNewMarcher){
            Marcher newMarcher = createMarcher();
            viewProfile(newMarcher);
        }
        else if(event.getSource() == btnDeleteMarcher){
            deleteMarcher(memberListView.getFocusModel().getFocusedItem());
        }
    }

    @FXML
    void perfClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY &&
        event.getClickCount() == 2){
            // get the focused member from the list
            viewProfile(memberListView.getFocusModel().getFocusedItem());
        }
    }

    @FXML
    void actionDelete(KeyEvent event){
        if (event.getCode() == KeyCode.DELETE){
            deleteMarcher(memberListView.getFocusModel().getFocusedItem());
        }
    }

    Marcher createMarcher(){
        Marcher newMarcher = new Marcher();
        data.addElement(newMarcher);
        memberListView.setItems(data.getData());
        memberListView.refresh();
        return newMarcher;

    }

    void deleteMarcher(Marcher marcher){
        data.removeElement(marcher);
        memberListView.refresh();
    }

    void viewProfile(Marcher marcher) {
        Stage memberStage = new Stage();
        // get an instance of the loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/memberView.fxml"));

        // load the scene and initialize the controller data
        try {
            memberStage.setScene(new Scene(loader.load()));
            MemberController memberController = loader.getController();
            memberController.initData(marcher, memberListView);

            // set the title and show the stage
            memberStage.setTitle(String.format("%s's Details", marcher));
            memberStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
