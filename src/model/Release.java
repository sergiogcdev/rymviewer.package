/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.xml.bind.annotation.*;

/**
 *
 * @author sergio
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "release")
@XmlType(propOrder = {"bandName", "album", "releaseDate", "rating"})
public class Release {
    
    @XmlElement(name = "bandName")
    private String bandName;
    @XmlElement(name = "album")
    private String album;
    @XmlElement(name = "releaseDate")
    private int releaseDate;
    @XmlElement(name = "rating")
    private int rating;
    
    public Release() {
        //Empty
    }
    
    public Release(String bandName, String album, int releaseDate, int rating) {
        this.bandName = bandName;
        this.album = album;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{ " + "Band=" + bandName + ", Album=" + album + ", Release_Date=" + releaseDate + ", Rating=" + rating + " }";
    }
    
    
    
}
