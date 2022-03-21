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
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column
    private int id;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "view_permission", columnDefinition = "boolean default true")
    private boolean viewPermission;

    @Column(name = "archive_permission", columnDefinition = "boolean default false")
    private boolean archivePermission;

    @Column(name = "remove_permission", columnDefinition = "boolean default false")
    private boolean removePermission;

    @Column(name = "publish_permission", columnDefinition = "boolean default false")
    private boolean publishPermission;

    /**
     * Default constructor for a User.
     */
    public User() {
    }

    /**
     * Creates a user with default permissions.
     *
     * @param email     The user's email address.
     * @param password  The user's password.
     */
    public User(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    /**
     * Creates a user with non-standard permissions.
     *
     * @param email             The user's email address.
     * @param password          The user's password.
     * @param viewPermission    Permission to view the admin tool.
     * @param archivePermission Permission to archive pieces.
     * @param removePermission  Permission to delete pieces.
     * @param publishPermission Permission to unarchive pieces.
     */
    public User(String email, String password, boolean viewPermission, boolean archivePermission,
                boolean removePermission, boolean publishPermission) {
        this.setEmail(email);
        this.setPassword(password);
        this.setViewPermission(viewPermission);
        this.setArchivePermission(archivePermission);
        this.setRemovePermission(removePermission);
        this.setPublishPermission(publishPermission);
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
     * @param userId the user id
     */
    public void setId(int userId) {
        this.id = userId;
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return If the user has permission to archive pieces.
     */
    public boolean isArchivePermission() {
        return archivePermission;
    }

    /**
     * Sets if the user has archiving permissions.
     *
     * @param archivePermission the archive permission.
     */
    public void setArchivePermission(boolean archivePermission) {
        this.archivePermission = archivePermission;
    }

    /**
     * Verifies if the user has removing permissions.
     *
     * @return If the user has permission to remove pieces
     */
    public boolean isRemovePermission() {
        return removePermission;
    }

    /**
     * Sets if the user has removing permissions.
     *
     * @param removePermission the remove permission.
     */
    public void setRemovePermission(boolean removePermission) {
        this.removePermission = removePermission;
    }

    /**
     * Verifies if the user has publishing permissions.
     *
     * @return If the user has permission to publish pieces.
     */
    public boolean isPublishPermission() {
        return publishPermission;
    }

    /**
     * Sets if the user has publishing permissions.
     *
     * @param publishPermission the publish permission.
     */
    public void setPublishPermission(boolean publishPermission) {
        this.publishPermission = publishPermission;
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

        if (id != user.id) return false;
        if (viewPermission != user.viewPermission) return false;
        if (archivePermission != user.archivePermission) return false;
        if (removePermission != user.removePermission) return false;
        if (publishPermission != user.publishPermission) return false;
        if (!email.equals(user.email)) return false;
        return password.equals(user.password);
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
                ", password='" + password + '\'' +
                ", viewPermission=" + viewPermission +
                ", archivePermission=" + archivePermission +
                ", removePermission=" + removePermission +
                ", publishPermission=" + publishPermission +
                '}';
    }
}
