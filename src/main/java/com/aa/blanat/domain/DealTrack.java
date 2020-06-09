package com.aa.blanat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DealTrack.
 */
@Entity
@Table(name = "deal_track")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealTrack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ip_localisation", nullable = false)
    private String ipLocalisation;

    @Column(name = "localisation")
    private String localisation;

    @NotNull
    @Column(name = "is_authentified", nullable = false)
    private Boolean isAuthentified;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dealTracks", allowSetters = true)
    private Deal deal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpLocalisation() {
        return ipLocalisation;
    }

    public DealTrack ipLocalisation(String ipLocalisation) {
        this.ipLocalisation = ipLocalisation;
        return this;
    }

    public void setIpLocalisation(String ipLocalisation) {
        this.ipLocalisation = ipLocalisation;
    }

    public String getLocalisation() {
        return localisation;
    }

    public DealTrack localisation(String localisation) {
        this.localisation = localisation;
        return this;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Boolean isIsAuthentified() {
        return isAuthentified;
    }

    public DealTrack isAuthentified(Boolean isAuthentified) {
        this.isAuthentified = isAuthentified;
        return this;
    }

    public void setIsAuthentified(Boolean isAuthentified) {
        this.isAuthentified = isAuthentified;
    }

    public Deal getDeal() {
        return deal;
    }

    public DealTrack deal(Deal deal) {
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
        if (!(o instanceof DealTrack)) {
            return false;
        }
        return id != null && id.equals(((DealTrack) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealTrack{" +
            "id=" + getId() +
            ", ipLocalisation='" + getIpLocalisation() + "'" +
            ", localisation='" + getLocalisation() + "'" +
            ", isAuthentified='" + isIsAuthentified() + "'" +
            "}";
    }
}
