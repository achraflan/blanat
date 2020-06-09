package com.aa.blanat.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.aa.blanat.domain.DealComment} entity.
 */
public class DealCommentDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 1000)
    private String comment;

    private LocalDate dateComment;

    private Boolean isActive;

    private Boolean isDeleted;

    private Long like;

    private Long dislike;

    private LocalDate dateLastModification;


    private Long assignedToId;

    private Long parentId;

    private Long dealId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateComment() {
        return dateComment;
    }

    public void setDateComment(LocalDate dateComment) {
        this.dateComment = dateComment;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Long getDislike() {
        return dislike;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public LocalDate getDateLastModification() {
        return dateLastModification;
    }

    public void setDateLastModification(LocalDate dateLastModification) {
        this.dateLastModification = dateLastModification;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long dealUserId) {
        this.assignedToId = dealUserId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long dealCommentId) {
        this.parentId = dealCommentId;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealCommentDTO)) {
            return false;
        }

        return id != null && id.equals(((DealCommentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealCommentDTO{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", dateComment='" + getDateComment() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", like=" + getLike() +
            ", dislike=" + getDislike() +
            ", dateLastModification='" + getDateLastModification() + "'" +
            ", assignedToId=" + getAssignedToId() +
            ", parentId=" + getParentId() +
            ", dealId=" + getDealId() +
            "}";
    }
}
