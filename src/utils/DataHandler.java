package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Marcher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandler<T> {

    private ObservableList<T> data;

    public DataHandler(){
        data = FXCollections.observableArrayList();
    }

    public DataHandler(File file){
        data = FXCollections.observableArrayList();
        importData(file);
    }

    public void addElement(T e){
        data.add(e);
    }

    public void removeElement(T e){
        data.remove(e);
    }

    public ObservableList<T> getData(){
        return data;
    }

    public void importData(ObservableList<T> lst){
        data.addAll(lst);
    }

    public void importData(File file) {
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fin);
            int len = in.readInt();
            for (int i = 0; i < len; i++){
                try {
                    T el = (T) in.readObject();
                    addElement(el);
                } catch (ClassNotFoundException e){
                    e.getException();
                }
            }
            fin.close();
            in.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void exportData(File file){
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeInt(data.size());
            for (T el : data){
                out.writeObject(el);
            }
            out.flush();
            fout.close();
            out.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(String.format("Exported to %s",file.getAbsolutePath()));
    }

}
