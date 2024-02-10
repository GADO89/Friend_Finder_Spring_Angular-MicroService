package com.use.management.model;

import java.time.LocalDateTime;

public abstract class BaseEntity {

    private Long id;

    private boolean active;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Long moifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getMoifiedBy() {
        return moifiedBy;
    }

    public void setMoifiedBy(Long moifiedBy) {
        this.moifiedBy = moifiedBy;
    }
}
