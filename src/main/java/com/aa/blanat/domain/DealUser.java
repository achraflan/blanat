package com.aa.blanat.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.aa.blanat.domain.enumeration.Gender;

/**
 * A DealUser.
 */
@Entity
@Table(name = "deal_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Pattern(regexp = "^(?:0|\\(?\\+212\\)?\\s?|00212\\s?)[1-79](?:[\\.\\-\\s]?\\d\\d)")
    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "comment")
    private Boolean comment;

    @Column(name = "notification")
    private Boolean notification;

    @Column(name = "reporting")
    private Boolean reporting;

    @Column(name = "email_notification")
    private Boolean emailNotification;

    @Column(name = "message")
    private Boolean message;

    @OneToOne(optional = false)
    @NotNull

    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "deal_user_deal_saved",
               joinColumns = @JoinColumn(name = "deal_user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "deal_saved_id", referencedColumnName = "id"))
    private Set<Deal> dealSaveds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public DealUser gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public DealUser phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public DealUser address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public DealUser city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public DealUser birthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean isComment() {
        return comment;
    }

    public DealUser comment(Boolean comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }

    public Boolean isNotification() {
        return notification;
    }

    public DealUser notification(Boolean notification) {
        this.notification = notification;
        return this;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public Boolean isReporting() {
        return reporting;
    }

    public DealUser reporting(Boolean reporting) {
        this.reporting = reporting;
        return this;
    }

    public void setReporting(Boolean reporting) {
        this.reporting = reporting;
    }

    public Boolean isEmailNotification() {
        return emailNotification;
    }

    public DealUser emailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
        return this;
    }

    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public Boolean isMessage() {
        return message;
    }

    public DealUser message(Boolean message) {
        this.message = message;
        return this;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public DealUser user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Deal> getDealSaveds() {
        return dealSaveds;
    }

    public DealUser dealSaveds(Set<Deal> deals) {
        this.dealSaveds = deals;
        return this;
    }

    public DealUser addDealSaved(Deal deal) {
        this.dealSaveds.add(deal);
        deal.getDealUsers().add(this);
        return this;
    }

    public DealUser removeDealSaved(Deal deal) {
        this.dealSaveds.remove(deal);
        deal.getDealUsers().remove(this);
        return this;
    }

    public void setDealSaveds(Set<Deal> deals) {
        this.dealSaveds = deals;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealUser)) {
            return false;
        }
        return id != null && id.equals(((DealUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealUser{" +
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
            "}";
    }
}
