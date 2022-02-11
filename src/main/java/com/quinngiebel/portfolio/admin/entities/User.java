package com.quinngiebel.portfolio.admin.entities;

import javax.persistence.*;

/**
 * This class is an entity to store user information.
 *
 * @author Quinn Giebel
 */
@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private String userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
