package utils;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import models.Marcher;

public class MarcherCellFactory implements Callback<ListView<Marcher>, ListCell<Marcher>>{
    @Override
    public ListCell<Marcher> call(ListView<Marcher> listview)
    {
        return new MarcherCell();
    }
}

