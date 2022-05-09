package com.quinngiebel.admin.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * The entity Category.
 */
@Entity(name = "Category")
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Piece> pieces;

    /**
     * Instantiates a new Category.
     */
    public Category() {
        pieces = new HashSet<>();
    }


    /**
     * Instantiates a new Category.
     *
     * @param name the name
     */
    public Category(String name) {
        this.name = name;
        pieces = new HashSet<>();
    }

    /**
     * Returns the name of the category in title case.
     *
     * @return The name of the category in title case.
     */
    public String getTitle() {
        String[] nameArray = this.name.split(" ");
        StringBuilder retString = new StringBuilder();

        // if name is only one word
        if (nameArray.length == 1) {
            return retString.append(this.name.substring(0, 1).toUpperCase(Locale.ROOT))
                    .append(this.name.substring(1).toLowerCase(Locale.ROOT))
                    .toString();
        }

        // if name is multiple words
        for (String word : nameArray) {
            retString.append(word.substring(0, 1).toUpperCase(Locale.ROOT))
                    .append(word.substring(1).toLowerCase(Locale.ROOT))
                    .append(" ");
        }
        return retString.substring(0,retString.length()-1);//removes trailing space
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets pieces.
     *
     * @return the pieces
     */
    public Set<Piece> getPieces() {
        return pieces;
    }

    /**
     * Sets pieces.
     *
     * @param pieces the pieces
     */
    public void setPieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    /**
     * Determines if another Object is equal to this instance of Category.
     *
     * @param o The Object being verified.
     * @return True if the instances are equivalent and false if they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        return name.equals(category.name);
    }

    /**
     * Returns a String representation of a category.
     *
     * @return A String representation of a category.
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Returns a JSON representation of a category.
     *
     * @return A JSON representation of a category.
     */
    public String toJSON() {
        return String.format("{\"id\":%d,\"name\":\"%s\"}",
                this.id, this.name);
    }
}
