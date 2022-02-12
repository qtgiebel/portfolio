package com.quinngiebel.portfolio.admin.entities;

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
    @GenericGenerator(name = "native",strategy = "Native")
    @Column(name = "user_id")
    private int id;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "view_permission")
    private boolean viewPermission;

    @Column(name = "archive_permission")
    private boolean archivePermission;

    @Column(name = "remove_permission")
    private boolean removePermission;

    @Column(name = "publish_permission")
    private boolean publishPermission;

    public User() {
    }

    public User(String email, String password, boolean viewPermission, boolean archivePermission,
                boolean removePermission, boolean publishPermission) {
        this.setEmail(email);
        this.setPassword(password);
        this.setViewPermission(viewPermission);
        this.setArchivePermission(archivePermission);
        this.setRemovePermission(removePermission);
        this.setPublishPermission(publishPermission);
    }

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isViewPermission() {
        return viewPermission;
    }

    public void setViewPermission(boolean viewPermission) {
        this.viewPermission = viewPermission;
    }

    public boolean isArchivePermission() {
        return archivePermission;
    }

    public void setArchivePermission(boolean archivePermission) {
        this.archivePermission = archivePermission;
    }

    public boolean isRemovePermission() {
        return removePermission;
    }

    public void setRemovePermission(boolean removePermission) {
        this.removePermission = removePermission;
    }

    public boolean isPublishPermission() {
        return publishPermission;
    }

    public void setPublishPermission(boolean publishPermission) {
        this.publishPermission = publishPermission;
    }
}
