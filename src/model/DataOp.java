/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergio
 */
public class DataOp {
    
    private List<Release> filtered;
    
    public DataOp() {
        this.filtered = new ArrayList<>();
    }
    
    public List<Release> searchByBandName(String data, List<Release> list) throws Exception{
        this.filtered.clear();
        for(Release r : list) {
            if(r.getBandName().toLowerCase().contains(data.toLowerCase())) this.filtered.add(r);
        }
        if(!this.filtered.isEmpty()) return this.filtered;
        return null;
    }
    
    public List<Release> searchByYear(String data, List<Release> list) throws Exception {
        this.filtered.clear();
        for(Release r : list) {
            if(String.valueOf(r.getReleaseDate()).equals(data.trim())) this.filtered.add(r);
        }
        if(!this.filtered.isEmpty()) return this.filtered;
        return null;
    }
    
    public List<Release> searchByRating(String data, List<Release> list) throws Exception{
        this.filtered.clear();
        for(Release r : list) {
            if(String.valueOf(r.getRating()).equals(data.trim())) this.filtered.add(r);
        }
        if(!this.filtered.isEmpty()) return this.filtered;
        return null;
    }
    
    public List<Release> searchByYearNRating(String data, List<Release> list) throws Exception {
        
        this.filtered.clear();
        String year = data.split(",")[0].trim();
        String rating = data.split(",")[1].trim();
        for(Release r : list) {
            if(String.valueOf(r.getReleaseDate()).equals(year) && String.valueOf(r.getRating()).equals(rating))
                this.filtered.add(r);
        }
        if(!this.filtered.isEmpty()) return this.filtered;
        return null;
    }
    
    public int getAverageRatingByYear(String data, List<Release> list) throws Exception {
        this.filtered.clear();
        int average = 0, cont = 0, sum = 0;
        for(Release r : list) {
            if(String.valueOf(r.getReleaseDate()).equals(data)) {
                sum += r.getRating();
                cont += 1;
            }
        }
        if(cont > 0) average = sum/cont;
        else average = 0;
        return average;
    }

    public List<Release> searchBetweenTwoRatings(String data, List<Release> list) throws Exception {
        this.filtered.clear();
        String rating1 = data.split(",")[0].trim();
        String rating2 = data.split(",")[1].trim();
        for(Release r : list) {
            if(r.getRating() >= Integer.valueOf(rating1) && r.getRating() <= Integer.valueOf(rating2))
                this.filtered.add(r);
        }
        if(!this.filtered.isEmpty()) return this.filtered;
        return null;
        
    }

    public List<Release> searchBetweenTwoRatingsByYear(String data, List<Release> list) throws Exception {
    this.filtered.clear();
        String rating1 = data.split(",")[0].trim();
        String rating2 = data.split(",")[1].trim();
        String year = data.split(",")[2].trim();
        for(Release r : list) {
            if(String.valueOf(r.getReleaseDate()).equals(year)){
                if(r.getRating() >= Integer.valueOf(rating1) && r.getRating() <= Integer.valueOf(rating2))
                this.filtered.add(r);
            }
        }
        if(!this.filtered.isEmpty()) return this.filtered;
        return null;
    }
    
    public List<Release> getFiltered() {
        return this.filtered;
    }
    
}
