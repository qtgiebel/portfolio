package com.quinngiebel.admin.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public Category() {
        pieces = new HashSet<>();
    }

    public Category(String name) {
        this.name = name;
        pieces = new HashSet<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    /**
     * Determines if another Object is equal to this instance of Category.
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
     * @return A JSON representation of a category.
     */
    public String toJSON() {
        return String.format("{\"id\":%d,\"name\":\"%s\"}",
                this.id, this.name);
    }
}
