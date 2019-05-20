package utils;

import models.Marcher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandler {

    private List<Marcher> dataList;

    public DataHandler(){
        dataList = new ArrayList<>();
    }

    private void addElement(Marcher e){
        dataList.add(e);
    }

    public List<Marcher> getData(){
        return dataList;
    }


    public void importData(String dataFile){
        try{
            Scanner scanner = new Scanner(new File(dataFile));

            String line;
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                String[] sarr = line.strip().split(",");
                if (sarr.length == 4){
                    this.addElement(new Marcher(sarr[0],sarr[1],sarr[2],sarr[3]));
                }
                else if (sarr.length == 6){
                    this.addElement(new Marcher(sarr[0],sarr[1],sarr[2],sarr[3],
                            Integer.parseInt(sarr[4]),Integer.parseInt(sarr[5])));
                }
                else{
                    System.out.println(String.format("Invalid format: %s", line));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void exportData(String dataFile){
        try {
            //URL url = getClass().getResource(dataFile);
            FileWriter writer = new FileWriter(new File(dataFile));

            dataList.forEach(m -> {
                try{
                    writer.append(m.asCSV());
                    writer.append("\n");
                } catch (IOException e){
                    e.printStackTrace();
                }
            });
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
