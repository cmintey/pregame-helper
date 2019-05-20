package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Marcher;

public class MemberController {

    @FXML
    private ImageView memberPic;

    @FXML
    private Label memberName;

    @FXML
    private TextField txtPart;

    @FXML
    private TextField txtYear;

    @FXML
    private TextArea txtSpots;

    @FXML
    private Button btnUpdate;

    private Marcher marcher;

    @FXML
    void initData(Marcher marcher){
        this.marcher = marcher;
        memberName.setText(marcher.getName());
        txtPart.setText(marcher.getPart());
        txtYear.setText(marcher.getYear());
        txtSpots.setText(marcher.getSpots());
        try{
            memberPic.setImage(new Image(String.format("/resources/%s.jpg", marcher.getName())));
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void updateMember(ActionEvent event) {
        if (event.getSource() == btnUpdate){
            this.marcher.setPart(txtPart.getText());
            this.marcher.setYear(txtYear.getText());
            this.marcher.setSpots(txtSpots.getText());
        }
    }

}
