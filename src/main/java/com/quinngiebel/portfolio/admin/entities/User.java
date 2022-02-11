package com.quinngiebel.portfolio.admin.entities;

public class User {
    private String userId;
    private String email;
    private String password;
    private boolean viewPermission;
    private boolean archivePermission;
    private boolean removePermission;
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
