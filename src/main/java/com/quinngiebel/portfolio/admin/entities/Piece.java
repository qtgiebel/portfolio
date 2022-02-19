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

    public Piece() {
    }

    /**
     *
     * @param title
     * @param location
     * @param category
     */
    public Piece(String title, String location, Category category) {
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
    public Piece(String title, String location, Category category, boolean isArchived) {
        this.title = title;
        this.location = location;
        this.archived = isArchived;
        this.category = category;
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
    public Category getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     *
     * @param archived
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    /**
     *
     * @param o
     * @return
     */
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
                ", category='[" + category.toString() + "]'" +
                ", isArchived=" + archived +
                '}';
    }
}
