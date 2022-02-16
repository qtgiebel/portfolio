package com.quinngiebel.portfolio.admin.entities;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
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
    @GenericGenerator(name = "native",strategy = "native")
    @Column
    private int id;

    @Column
    private String title;

    @Column
    private String location;

    @Column
    private String category;

    @Column(name = "is_archived", columnDefinition = "boolean default false")
    private boolean isArchived;

    public Piece() {
    }

    /**
     *
     * @param title
     * @param location
     * @param category
     */
    public Piece(String title, String location, String category) {
        this.title = title;
        this.location = location;
        this.category = category;
    }

    /**
     *
     * @param title
     * @param location
     * @param category
     * @param isArchived
     */
    public Piece(String title, String location, String category, boolean isArchived) {
        this.title = title;
        this.location = location;
        this.category = category;
        this.isArchived = isArchived;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param artId
     */
    public void setId(int artId) {
        this.id = artId;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param altText
     */
    public void setTitle(String altText) {
        this.title = altText;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public boolean isArchived() {
        return isArchived;
    }

    /**
     *
     * @param isArchived
     */
    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Piece{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", isArchived=" + isArchived +
                '}';
    }
}
