/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;

/**
 *
 * @author sergio
 */
public class OptionList {
    
    private List<Option> options;
    
    public OptionList() {
        //Empty
    }
    
    public OptionList(List<Option> options) {
        this.options = options;
    }

    public List<Option> getOptions() {
        return options;
    }
    
    
}
