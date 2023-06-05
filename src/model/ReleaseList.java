/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergio
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "catalog")
public class ReleaseList {
    
    @XmlElementWrapper(name="releases")
    @XmlElement(name="release") 
    private List<Release> releases;
    private List <Release> filtered;
    
    public ReleaseList(){
        //Empty
    }
    public ReleaseList(List<Release> releases) {
        this.releases = releases;
        this.filtered = new ArrayList<>();
    }
    
    

    public List<Release> getReleases() {
        return releases;
    }

    public List<Release> getFiltered() {
        return filtered;
    }
    
    
    
}
