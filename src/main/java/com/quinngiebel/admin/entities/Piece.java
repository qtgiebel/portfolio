package com.quinngiebel.admin.entities;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column
    private int id;

    @Column
    private String title;

    @Column
    private String location;

    @Column(name = "is_archived", columnDefinition = "boolean default false")
    private boolean archived;

    @ManyToOne
    private Category category;

    /**
     * Instantiates a new Piece.
     */
    public Piece() {
    }


    /**
     * Instantiates a new Piece.
     *
     * @param title    the title
     * @param location the location
     * @param category the category
     */
    public Piece(String title, String location, Category category) {
        this.title = title;
        this.location = location;
        this.category = category;
    }


    /**
     * Instantiates a new Piece.
     *
     * @param title      the title
     * @param location   the location
     * @param category   the category
     * @param isArchived if the piece is archived
     */
    public Piece(String title, String location, Category category, boolean isArchived) {
        this.title = title;
        this.location = location;
        this.archived = isArchived;
        this.category = category;
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJSON() {
        return String.format(
                "{\"id\":%d,\"title\":\"%s\", \"location\":\"%s\", \"archived\":\"%b\", \"category\":%s}",
                this.id, this.title, this.location, this.archived, this.category.toJSON());
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets id.
     *
     * @param artId the art id
     */
    public void setId(int artId) {
        this.id = artId;
    }


    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Sets title.
     *
     * @param altText the alt text
     */
    public void setTitle(String altText) {
        this.title = altText;
    }


    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }


    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }


    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }


    /**
     * Is archived boolean.
     *
     * @return the boolean
     */
    public boolean isArchived() {
        return archived;
    }


    /**
     * Sets archived.
     *
     * @param archived the archived
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (id != piece.id) return false;
        if (category != piece.category) return false;
        if (archived != piece.archived) return false;
        if (!title.equals(piece.title)) return false;
        return location.equals(piece.location);
    }


    @Override
    public String toString() {
        return "Piece{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", category='[" + category.toString() + "]'" +
                ", isArchived=" + archived +
                '}';
    }
}
