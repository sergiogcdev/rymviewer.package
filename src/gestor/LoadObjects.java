/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Release;
import model.ReleaseList;

/**
 *
 * @author sergio
 */
public class LoadObjects {
    
    private List<Release> filtered = new ArrayList<>();
    
    
    public void main() {
        
        ReleaseList releases;
        List<Release> list = new ArrayList<>();
        
        try {
            int cont = 0;
            
            File file = new File("resources/user_albums");
            Scanner sc = new Scanner(file);
            
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(cont == 0) {
                    cont+=1;
                    continue;
                }
                String bandName = line.split(",\"")[2].replace("\"", "");
                String album = line.split(",\"")[5].replace("\"", "");
                String releaseDateStr = line.split(",\"")[6].replace("\"", "");
                int releaseDate = Integer.valueOf(releaseDateStr);
                String ratingStr = line.split(",\"")[7].replace("\"", "");
                int rating = Integer.valueOf(ratingStr);
                
                Release r = new Release(bandName, album, releaseDate, rating);
                list.add(r);
                
                cont+=1;
            }
            
            releases = new ReleaseList(list);
            File destFile = new File("resources/user_albums.xml");
            JAXBContext jaxbContext = null;
            jaxbContext = JAXBContext.newInstance(ReleaseList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(releases, destFile);
            
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public StringBuilder execute(String selected, String data) {
        StringBuilder sb = new StringBuilder();
        try {
            
            JAXBContext jaxbContext = null;
            File sourceFile = new File("./resources/user_albums.xml");
            jaxbContext = JAXBContext.newInstance(ReleaseList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ReleaseList releaseList = (ReleaseList) jaxbUnmarshaller.unmarshal(sourceFile);
            List<Release> list = releaseList.getReleases();
            
            System.out.println("Results:");
            
            switch(selected) {
                case "1":
                    for(Release r: this.searchByBandName(data, list))
                    {
                        sb.append(r.toString() + "\n");
                    }
                    break;
                case "2":
                    for(Release r: this.searchByYear(data, list))
                    {
                        sb.append(r.toString() + "\n");
                    }
                    break;
                case "3":
                    for(Release r: this.searchByRating(data, list))
                    {
                        sb.append(r.toString() + "\n");
                    }
                    break;
                case "4":
                    for(Release r: this.searchByYearNRating(data, list))
                    {
                        sb.append(r.toString() + "\n");
                    }
                    break;
                case "5":
                    int rating = this.getAverageRatingByYear(data, list);
                    if(rating > 0) sb.append( "Average rating of " + data + ": " + rating + "\n");
                    else sb.append("null");
                    break;
                case "6":
                    for(Release r: this.searchBetweenTwoRatings(data, list))
                    {
                        
                        sb.append(r.toString() + "\n");
                    }
                    break;
                case "7":
                    System.exit(0);
                    break;
            }
            
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sb;
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
            if(String.valueOf(r.getReleaseDate()).equals(data)) this.filtered.add(r);
        }
        if(!this.filtered.isEmpty()) return this.filtered;
        return null;
    }
    
    public List<Release> searchByRating(String data, List<Release> list) throws Exception{
        this.filtered.clear();
        for(Release r : list) {
            if(String.valueOf(r.getRating()).equals(data)) this.filtered.add(r);
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
    
    private int getAverageRatingByYear(String data, List<Release> list) throws Exception {
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

    private List<Release> searchBetweenTwoRatings(String data, List<Release> list) throws Exception {
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
    
}
