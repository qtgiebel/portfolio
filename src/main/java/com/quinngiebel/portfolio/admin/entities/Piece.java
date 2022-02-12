package com.quinngiebel.portfolio.admin.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * This class is an entity to store art information.
 *
 * @author Quinn Giebel
 */
@Entity(name = "Piece")
@Table(name = "pieces")
public class Piece {
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "Native")
    @Column
    private int id;

    @Column(name = "alt_text")
    private int altText;

    @Column
    private String location;

    @Column
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int artId) {
        this.id = artId;
    }

    public int getAltText() {
        return altText;
    }

    public void setAltText(int altText) {
        this.altText = altText;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
