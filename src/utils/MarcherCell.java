package utils;

import javafx.scene.control.ListCell;
import models.Marcher;

public class MarcherCell extends ListCell<Marcher>{
    @Override
    public void updateItem(Marcher item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null){
            this.setText(item.getName());
        }
        setGraphic(null);
    }
}