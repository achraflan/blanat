package com.aa.blanat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A DealComment.
 */
@Entity
@Table(name = "deal_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 1000)
    @Column(name = "comment", length = 1000, nullable = false)
    private String comment;

    @Column(name = "date_comment")
    private LocalDate dateComment;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "jhi_like")
    private Long like;

    @Column(name = "dislike")
    private Long dislike;

    @Column(name = "date_last_modification")
    private LocalDate dateLastModification;

    @OneToMany(mappedBy = "dealComment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CommentHistory> dealHistories = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dealComments", allowSetters = true)
    private DealUser assignedTo;

    @ManyToOne
    @JsonIgnoreProperties(value = "dealComments", allowSetters = true)
    private DealComment parent;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dealComments", allowSetters = true)
    private Deal deal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public DealComment comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateComment() {
        return dateComment;
    }

    public DealComment dateComment(LocalDate dateComment) {
        this.dateComment = dateComment;
        return this;
    }

    public void setDateComment(LocalDate dateComment) {
        this.dateComment = dateComment;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public DealComment isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public DealComment isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getLike() {
        return like;
    }

    public DealComment like(Long like) {
        this.like = like;
        return this;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Long getDislike() {
        return dislike;
    }

    public DealComment dislike(Long dislike) {
        this.dislike = dislike;
        return this;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public LocalDate getDateLastModification() {
        return dateLastModification;
    }

    public DealComment dateLastModification(LocalDate dateLastModification) {
        this.dateLastModification = dateLastModification;
        return this;
    }

    public void setDateLastModification(LocalDate dateLastModification) {
        this.dateLastModification = dateLastModification;
    }

    public Set<CommentHistory> getDealHistories() {
        return dealHistories;
    }

    public DealComment dealHistories(Set<CommentHistory> commentHistories) {
        this.dealHistories = commentHistories;
        return this;
    }

    public DealComment addDealHistory(CommentHistory commentHistory) {
        this.dealHistories.add(commentHistory);
        commentHistory.setDealComment(this);
        return this;
    }

    public DealComment removeDealHistory(CommentHistory commentHistory) {
        this.dealHistories.remove(commentHistory);
        commentHistory.setDealComment(null);
        return this;
    }

    public void setDealHistories(Set<CommentHistory> commentHistories) {
        this.dealHistories = commentHistories;
    }

    public DealUser getAssignedTo() {
        return assignedTo;
    }

    public DealComment assignedTo(DealUser dealUser) {
        this.assignedTo = dealUser;
        return this;
    }

    public void setAssignedTo(DealUser dealUser) {
        this.assignedTo = dealUser;
    }

    public DealComment getParent() {
        return parent;
    }

    public DealComment parent(DealComment dealComment) {
        this.parent = dealComment;
        return this;
    }

    public void setParent(DealComment dealComment) {
        this.parent = dealComment;
    }

    public Deal getDeal() {
        return deal;
    }

    public DealComment deal(Deal deal) {
        this.deal = deal;
        return this;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealComment)) {
            return false;
        }
        return id != null && id.equals(((DealComment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealComment{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", dateComment='" + getDateComment() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", like=" + getLike() +
            ", dislike=" + getDislike() +
            ", dateLastModification='" + getDateLastModification() + "'" +
            "}";
    }
}
