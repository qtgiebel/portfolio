package com.quinngiebel.admin.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * This class is an entity to store user information.
 *
 * @author Quinn Giebel
 */
@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @Column
    private String id;

    @Column
    private String email;

    @Column(name = "view_permission", columnDefinition = "boolean default true")
    private boolean viewPermission;

    @Column(name = "admin_permission", columnDefinition = "boolean default false")
    private boolean adminPermission;

    /**
     * Default constructor for a User.
     */
    public User() {
    }

    /**
     * Creates a user with default permissions.
     *
     * @param id        The user's cognito id.
     * @param email     The user's email address.
     */
    public User(String id, String email) {
        this.setId(id);
        this.setEmail(email);
    }

    /**
     * Creates a user with non-standard permissions.
     *
     * @param id                The user id.
     * @param email             The user's email.
     * @param viewPermission    Permission to view the admin tool.
     * @param adminPermission   Permission to use the admin tool.
     */
    public User(String id, String email, boolean viewPermission, boolean adminPermission) {
        this.setId(id);
        this.setEmail(email);
        this.setViewPermission(viewPermission);
        this.setAdminPermission(adminPermission);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJSON() {
        return String.format(
                "{\"id\":\"%s\",\"email\":\"%s\", \"viewPermission\":%b, \"adminPermission\":%b}",
                this.id, this.email, this.viewPermission, this.adminPermission);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the user id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

        /**
     * Verifies if the user has viewing permissions.
     *
     * @return If the user has permission to view pieces
     */
    public boolean isViewPermission() {
        return viewPermission;
    }

    /**
     * Sets if the user has viewing permissions.
     *
     * @param viewPermission the view permission
     */
    public void setViewPermission(boolean viewPermission) {
        this.viewPermission = viewPermission;
    }

    /**
     * Verifies if the user has archiving permissions.
     *
     * @return If the user has admin permissions.
     */
    public boolean isAdminPermission() {
        return adminPermission;
    }

    /**
     * Sets if the user has archiving permissions.
     *
     * @param adminPermission the archive permission.
     */
    public void setAdminPermission(boolean adminPermission) {
        this.adminPermission = adminPermission;
    }

    /**
     * Verifies a User object is equal to another object.
     *
     * @param o The object being verified.
     * @return  If the two objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (viewPermission != user.viewPermission) return false;
        if (adminPermission != user.adminPermission) return false;
        return email.equals(user.email);
    }

    /**
     * Returns a string representation of a User.
     *
     * @return A string representation of a User.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", viewPermission=" + viewPermission +
                ", adminPermission=" + adminPermission +
                '}';
    }
}
