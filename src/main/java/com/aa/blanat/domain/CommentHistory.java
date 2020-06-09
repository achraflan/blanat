package com.aa.blanat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CommentHistory.
 */
@Entity
@Table(name = "comment_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 1000)
    @Column(name = "comment", length = 1000, nullable = false)
    private String comment;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dealHistories", allowSetters = true)
    private DealComment dealComment;

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

    public CommentHistory comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public CommentHistory dateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public DealComment getDealComment() {
        return dealComment;
    }

    public CommentHistory dealComment(DealComment dealComment) {
        this.dealComment = dealComment;
        return this;
    }

    public void setDealComment(DealComment dealComment) {
        this.dealComment = dealComment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentHistory)) {
            return false;
        }
        return id != null && id.equals(((CommentHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommentHistory{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            "}";
    }
}
