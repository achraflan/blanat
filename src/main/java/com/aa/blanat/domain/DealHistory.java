package com.aa.blanat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DealHistory.
 */
@Entity
@Table(name = "deal_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "attribut_name", nullable = false)
    private String attributName;

    @NotNull
    @Size(max = 10000)
    @Column(name = "attribut_last_value", length = 10000, nullable = false)
    private String attributLastValue;

    @NotNull
    @Column(name = "date_modification", nullable = false)
    private LocalDate dateModification;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dealHistories", allowSetters = true)
    private Deal deal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributName() {
        return attributName;
    }

    public DealHistory attributName(String attributName) {
        this.attributName = attributName;
        return this;
    }

    public void setAttributName(String attributName) {
        this.attributName = attributName;
    }

    public String getAttributLastValue() {
        return attributLastValue;
    }

    public DealHistory attributLastValue(String attributLastValue) {
        this.attributLastValue = attributLastValue;
        return this;
    }

    public void setAttributLastValue(String attributLastValue) {
        this.attributLastValue = attributLastValue;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public DealHistory dateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public Deal getDeal() {
        return deal;
    }

    public DealHistory deal(Deal deal) {
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
        if (!(o instanceof DealHistory)) {
            return false;
        }
        return id != null && id.equals(((DealHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealHistory{" +
            "id=" + getId() +
            ", attributName='" + getAttributName() + "'" +
            ", attributLastValue='" + getAttributLastValue() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            "}";
    }
}
