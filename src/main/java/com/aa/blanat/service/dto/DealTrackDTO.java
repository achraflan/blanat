package com.aa.blanat.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.aa.blanat.domain.DealTrack} entity.
 */
public class DealTrackDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String ipLocalisation;

    private String localisation;

    @NotNull
    private Boolean isAuthentified;


    private Long dealId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpLocalisation() {
        return ipLocalisation;
    }

    public void setIpLocalisation(String ipLocalisation) {
        this.ipLocalisation = ipLocalisation;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Boolean isIsAuthentified() {
        return isAuthentified;
    }

    public void setIsAuthentified(Boolean isAuthentified) {
        this.isAuthentified = isAuthentified;
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
        if (!(o instanceof DealTrackDTO)) {
            return false;
        }

        return id != null && id.equals(((DealTrackDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealTrackDTO{" +
            "id=" + getId() +
            ", ipLocalisation='" + getIpLocalisation() + "'" +
            ", localisation='" + getLocalisation() + "'" +
            ", isAuthentified='" + isIsAuthentified() + "'" +
            ", dealId=" + getDealId() +
            "}";
    }
}
