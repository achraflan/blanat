package com.aa.blanat.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.aa.blanat.domain.CommentHistory} entity.
 */
public class CommentHistoryDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 1000)
    private String comment;

    private LocalDate dateModification;


    private Long dealCommentId;
    
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

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public Long getDealCommentId() {
        return dealCommentId;
    }

    public void setDealCommentId(Long dealCommentId) {
        this.dealCommentId = dealCommentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentHistoryDTO)) {
            return false;
        }

        return id != null && id.equals(((CommentHistoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommentHistoryDTO{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            ", dealCommentId=" + getDealCommentId() +
            "}";
    }
}
