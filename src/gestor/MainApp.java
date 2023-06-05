/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor;

import java.util.Scanner;
import view.Menu;

/**
 *
 * @author sergio
 */
public class MainApp {
    
    public static void main(String[] args) {
        
        LoadObjects loader = new LoadObjects();
        Menu menu = new Menu();
        
        menu.printMessage("Do you want to load data? [y/N]");
        Scanner scan = new Scanner(System.in);
        String c = scan.nextLine();
        if(c.equals("y") || c.equals("Y")) loader.main();
        else menu.printMessage("Data not loaded");
        menu.executeMenu();
        String data = menu.getData();
        String selected = menu.getSelectedOption();
        StringBuilder sb = loader.execute(selected, data);
        menu.printMessage(sb.toString());
        menu.printMessage("Do you want to save the results in a XML document? [y/N]");
        c = scan.nextLine();
        if(c.equals("y") || c.equals("Y")) {
            menu.printMessage("Introduce the filename (without format)");
            String fileName = scan.nextLine();
            if(loader.extractResultsIntoFile(fileName) == 1) menu.printMessage("The results are saved in resources/" + fileName + ".xml");
            else menu.printMessage("The results cannot be saved");
        }
    }
    
}
