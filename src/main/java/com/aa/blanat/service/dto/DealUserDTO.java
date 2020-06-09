package com.aa.blanat.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.aa.blanat.domain.enumeration.Gender;

/**
 * A DTO for the {@link com.aa.blanat.domain.DealUser} entity.
 */
public class DealUserDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Gender gender;

    @Pattern(regexp = "^(?:0|\\(?\\+212\\)?\\s?|00212\\s?)[1-79](?:[\\.\\-\\s]?\\d\\d)")
    private String phone;

    private String address;

    private String city;

    private LocalDate birthDay;

    private Boolean comment;

    private Boolean notification;

    private Boolean reporting;

    private Boolean emailNotification;

    private Boolean message;


    private Long userId;
    private Set<DealDTO> dealSaveds = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean isComment() {
        return comment;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }

    public Boolean isNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public Boolean isReporting() {
        return reporting;
    }

    public void setReporting(Boolean reporting) {
        this.reporting = reporting;
    }

    public Boolean isEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public Boolean isMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<DealDTO> getDealSaveds() {
        return dealSaveds;
    }

    public void setDealSaveds(Set<DealDTO> deals) {
        this.dealSaveds = deals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealUserDTO)) {
            return false;
        }

        return id != null && id.equals(((DealUserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealUserDTO{" +
            "id=" + getId() +
            ", gender='" + getGender() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", birthDay='" + getBirthDay() + "'" +
            ", comment='" + isComment() + "'" +
            ", notification='" + isNotification() + "'" +
            ", reporting='" + isReporting() + "'" +
            ", emailNotification='" + isEmailNotification() + "'" +
            ", message='" + isMessage() + "'" +
            ", userId=" + getUserId() +
            ", dealSaveds='" + getDealSaveds() + "'" +
            "}";
    }
}
