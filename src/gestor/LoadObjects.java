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
    
    public void execute(String selected, String data) {
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
                        System.out.println(r.toString());
                    }
                    break;
                case "2":
                    for(Release r: this.searchByYear(data, list))
                    {
                        System.out.println(r.toString());
                    }
                    break;
                case "3":
                    for(Release r: this.searchByRating(data, list))
                    {
                        System.out.println(r.toString());
                    }
                    break;
                case "4":
                    for(Release r: this.searchByYearNRating(data, list))
                    {
                        System.out.println(r.toString());
                    }
                    break;
                case "5":
                    System.exit(0);
                    break;
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Release> searchByBandName(String data, List<Release> list) throws Exception{
        this.filtered.clear();
        for(Release r : list) {
            if(r.getBandName().equals(data)) this.filtered.add(r);
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
    
}