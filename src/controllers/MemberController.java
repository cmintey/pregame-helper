package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @FXML
    void initData(String name, String part, String year, String spots){
        memberName.setText(name);
        txtPart.setText(part);
        txtYear.setText(year);
        txtSpots.setText(spots);
        try{
            memberPic.setImage(new Image(String.format("/resources/%s.jpg", name)));
        } catch (IllegalArgumentException e){
            System.out.println("Image not found!");
        }
    }

    @FXML
    void updateMember(ActionEvent event) {
        if (event.getSource() == btnUpdate){
            System.out.println("Updated!");
        }
    }

}
