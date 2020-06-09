package com.aa.blanat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DealCategory.
 */
@Entity
@Table(name = "deal_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "is_parent")
    private Boolean isParent;

    @ManyToOne
    @JsonIgnoreProperties(value = "dealCategories", allowSetters = true)
    private DealCategory category;

    @ManyToMany(mappedBy = "dealCategories")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Deal> deals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DealCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public DealCategory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsParent() {
        return isParent;
    }

    public DealCategory isParent(Boolean isParent) {
        this.isParent = isParent;
        return this;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public DealCategory getCategory() {
        return category;
    }

    public DealCategory category(DealCategory dealCategory) {
        this.category = dealCategory;
        return this;
    }

    public void setCategory(DealCategory dealCategory) {
        this.category = dealCategory;
    }

    public Set<Deal> getDeals() {
        return deals;
    }

    public DealCategory deals(Set<Deal> deals) {
        this.deals = deals;
        return this;
    }

    public DealCategory addDeal(Deal deal) {
        this.deals.add(deal);
        deal.getDealCategories().add(this);
        return this;
    }

    public DealCategory removeDeal(Deal deal) {
        this.deals.remove(deal);
        deal.getDealCategories().remove(this);
        return this;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealCategory)) {
            return false;
        }
        return id != null && id.equals(((DealCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", isParent='" + isIsParent() + "'" +
            "}";
    }
}
