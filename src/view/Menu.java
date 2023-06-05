/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sergio
 */
public class Menu {
    
    private OptionList mainMenu;
    private Scanner scan;
    private String selectedOption;
    private String data;
    
    public Menu() {
        this.mainMenu = this.loadMenu();
        this.scan = new Scanner(System.in);
        this.selectedOption = "0";
        this.data = "";
    }
    
    public void executeMenu(){
        for(Option op: this.mainMenu.getOptions()) {
            System.out.println(op.getId() + " - " + op.getDesc());
        }
        System.out.println("Introduce your option number: ");
        this.selectedOption = this.scan.nextLine();
        this.executeOption(this.selectedOption);
    }
    
    public OptionList loadMenu(){
        List<Option> list = new ArrayList<>();
        Option opt = new Option(1, "Search by band name");
        list.add(opt);
        opt = new Option(2, "Search by year");
        list.add(opt);
        opt = new Option(3, "Search by rating");
        list.add(opt);
        opt = new Option(4, "Search by year and rating");
        list.add(opt);
        opt = new Option(5, "Get average rating by year");
        list.add(opt);
        opt = new Option(6, "Get albums between two ratings");
        list.add(opt);
        opt = new Option(7, "Exit");
        list.add(opt);
        OptionList options = new OptionList(list);
        return options;
    }
    
    public void executeOption(String selected) {
        switch(selected) {
            case "1":
                System.out.println("Introduce the name of the band: ");
                this.data = this.scan.nextLine();
                break;
            case "2":
                System.out.println("Introduce the release date: ");
                this.data = this.scan.nextLine();
                break;
            case "3":
                System.out.println("Introduce the rating of the release: ");
                this.data = this.scan.nextLine();
                break;
            case "4":
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce the year of the release: ");
                String year = scanner.nextLine();
                System.out.println("Introduce the rating of the release: ");
                String rating = scanner.nextLine();
                this.data = year.trim() + ", " + rating.trim();
                break;
            case "5":
                System.out.println("Introduce the year of the releases: ");
                String averageYear = this.scan.nextLine();
                this.data = averageYear.trim();
                break;
            case "6":
                Scanner sc = new Scanner(System.in);
                System.out.println("Introduce the minimum rating: ");
                String rating1 = sc.nextLine();
                System.out.println("Introduce the maximum rating: ");
                String rating2 = sc.nextLine();
                this.data = rating1.trim() + ", " + rating2.trim();
                break;
            case "7":
                System.out.println("Saliendo...");
                this.data = "exit";
                break;
            default:
                System.out.println("Error");
                break;
        }
        
    }
    
    public void printMessage(String msg){
        System.out.println(msg);
    }

    public String getData() {
        return data;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    
    
}
