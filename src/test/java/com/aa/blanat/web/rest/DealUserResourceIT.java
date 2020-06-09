package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.DealUser;
import com.aa.blanat.domain.User;
import com.aa.blanat.repository.DealUserRepository;
import com.aa.blanat.service.DealUserService;
import com.aa.blanat.service.dto.DealUserDTO;
import com.aa.blanat.service.mapper.DealUserMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aa.blanat.domain.enumeration.Gender;
/**
 * Integration tests for the {@link DealUserResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealUserResourceIT {

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_PHONE = "00212654";
    private static final String UPDATED_PHONE = "0435";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DAY = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_COMMENT = false;
    private static final Boolean UPDATED_COMMENT = true;

    private static final Boolean DEFAULT_NOTIFICATION = false;
    private static final Boolean UPDATED_NOTIFICATION = true;

    private static final Boolean DEFAULT_REPORTING = false;
    private static final Boolean UPDATED_REPORTING = true;

    private static final Boolean DEFAULT_EMAIL_NOTIFICATION = false;
    private static final Boolean UPDATED_EMAIL_NOTIFICATION = true;

    private static final Boolean DEFAULT_MESSAGE = false;
    private static final Boolean UPDATED_MESSAGE = true;

    @Autowired
    private DealUserRepository dealUserRepository;

    @Mock
    private DealUserRepository dealUserRepositoryMock;

    @Autowired
    private DealUserMapper dealUserMapper;

    @Mock
    private DealUserService dealUserServiceMock;

    @Autowired
    private DealUserService dealUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealUserMockMvc;

    private DealUser dealUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealUser createEntity(EntityManager em) {
        DealUser dealUser = new DealUser()
            .gender(DEFAULT_GENDER)
            .phone(DEFAULT_PHONE)
            .address(DEFAULT_ADDRESS)
            .city(DEFAULT_CITY)
            .birthDay(DEFAULT_BIRTH_DAY)
            .comment(DEFAULT_COMMENT)
            .notification(DEFAULT_NOTIFICATION)
            .reporting(DEFAULT_REPORTING)
            .emailNotification(DEFAULT_EMAIL_NOTIFICATION)
            .message(DEFAULT_MESSAGE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        dealUser.setUser(user);
        return dealUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealUser createUpdatedEntity(EntityManager em) {
        DealUser dealUser = new DealUser()
            .gender(UPDATED_GENDER)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .birthDay(UPDATED_BIRTH_DAY)
            .comment(UPDATED_COMMENT)
            .notification(UPDATED_NOTIFICATION)
            .reporting(UPDATED_REPORTING)
            .emailNotification(UPDATED_EMAIL_NOTIFICATION)
            .message(UPDATED_MESSAGE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        dealUser.setUser(user);
        return dealUser;
    }

    @BeforeEach
    public void initTest() {
        dealUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealUser() throws Exception {
        int databaseSizeBeforeCreate = dealUserRepository.findAll().size();
        // Create the DealUser
        DealUserDTO dealUserDTO = dealUserMapper.toDto(dealUser);
        restDealUserMockMvc.perform(post("/api/deal-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealUserDTO)))
            .andExpect(status().isCreated());

        // Validate the DealUser in the database
        List<DealUser> dealUserList = dealUserRepository.findAll();
        assertThat(dealUserList).hasSize(databaseSizeBeforeCreate + 1);
        DealUser testDealUser = dealUserList.get(dealUserList.size() - 1);
        assertThat(testDealUser.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testDealUser.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testDealUser.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testDealUser.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testDealUser.getBirthDay()).isEqualTo(DEFAULT_BIRTH_DAY);
        assertThat(testDealUser.isComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testDealUser.isNotification()).isEqualTo(DEFAULT_NOTIFICATION);
        assertThat(testDealUser.isReporting()).isEqualTo(DEFAULT_REPORTING);
        assertThat(testDealUser.isEmailNotification()).isEqualTo(DEFAULT_EMAIL_NOTIFICATION);
        assertThat(testDealUser.isMessage()).isEqualTo(DEFAULT_MESSAGE);

        // Validate the id for MapsId, the ids must be same
        assertThat(testDealUser.getId()).isEqualTo(testDealUser.getUser().getId());
    }

    @Test
    @Transactional
    public void createDealUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealUserRepository.findAll().size();

        // Create the DealUser with an existing ID
        dealUser.setId(1L);
        DealUserDTO dealUserDTO = dealUserMapper.toDto(dealUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealUserMockMvc.perform(post("/api/deal-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealUser in the database
        List<DealUser> dealUserList = dealUserRepository.findAll();
        assertThat(dealUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void updateDealUserMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        dealUserRepository.saveAndFlush(dealUser);
        int databaseSizeBeforeCreate = dealUserRepository.findAll().size();

        // Add a new parent entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();

        // Load the dealUser
        DealUser updatedDealUser = dealUserRepository.findById(dealUser.getId()).get();
        // Disconnect from session so that the updates on updatedDealUser are not directly saved in db
        em.detach(updatedDealUser);

        // Update the User with new association value
        updatedDealUser.setUser(user);
        DealUserDTO updatedDealUserDTO = dealUserMapper.toDto(updatedDealUser);

        // Update the entity
        restDealUserMockMvc.perform(put("/api/deal-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDealUserDTO)))
            .andExpect(status().isOk());

        // Validate the DealUser in the database
        List<DealUser> dealUserList = dealUserRepository.findAll();
        assertThat(dealUserList).hasSize(databaseSizeBeforeCreate);
        DealUser testDealUser = dealUserList.get(dealUserList.size() - 1);

        // Validate the id for MapsId, the ids must be same
        // Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
        // Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
        // assertThat(testDealUser.getId()).isEqualTo(testDealUser.getUser().getId());
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealUserRepository.findAll().size();
        // set the field null
        dealUser.setGender(null);

        // Create the DealUser, which fails.
        DealUserDTO dealUserDTO = dealUserMapper.toDto(dealUser);


        restDealUserMockMvc.perform(post("/api/deal-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealUserDTO)))
            .andExpect(status().isBadRequest());

        List<DealUser> dealUserList = dealUserRepository.findAll();
        assertThat(dealUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealUsers() throws Exception {
        // Initialize the database
        dealUserRepository.saveAndFlush(dealUser);

        // Get all the dealUserList
        restDealUserMockMvc.perform(get("/api/deal-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].birthDay").value(hasItem(DEFAULT_BIRTH_DAY.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].notification").value(hasItem(DEFAULT_NOTIFICATION.booleanValue())))
            .andExpect(jsonPath("$.[*].reporting").value(hasItem(DEFAULT_REPORTING.booleanValue())))
            .andExpect(jsonPath("$.[*].emailNotification").value(hasItem(DEFAULT_EMAIL_NOTIFICATION.booleanValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDealUsersWithEagerRelationshipsIsEnabled() throws Exception {
        when(dealUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDealUserMockMvc.perform(get("/api/deal-users?eagerload=true"))
            .andExpect(status().isOk());

        verify(dealUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDealUsersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(dealUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDealUserMockMvc.perform(get("/api/deal-users?eagerload=true"))
            .andExpect(status().isOk());

        verify(dealUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDealUser() throws Exception {
        // Initialize the database
        dealUserRepository.saveAndFlush(dealUser);

        // Get the dealUser
        restDealUserMockMvc.perform(get("/api/deal-users/{id}", dealUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealUser.getId().intValue()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.birthDay").value(DEFAULT_BIRTH_DAY.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.booleanValue()))
            .andExpect(jsonPath("$.notification").value(DEFAULT_NOTIFICATION.booleanValue()))
            .andExpect(jsonPath("$.reporting").value(DEFAULT_REPORTING.booleanValue()))
            .andExpect(jsonPath("$.emailNotification").value(DEFAULT_EMAIL_NOTIFICATION.booleanValue()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDealUser() throws Exception {
        // Get the dealUser
        restDealUserMockMvc.perform(get("/api/deal-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealUser() throws Exception {
        // Initialize the database
        dealUserRepository.saveAndFlush(dealUser);

        int databaseSizeBeforeUpdate = dealUserRepository.findAll().size();

        // Update the dealUser
        DealUser updatedDealUser = dealUserRepository.findById(dealUser.getId()).get();
        // Disconnect from session so that the updates on updatedDealUser are not directly saved in db
        em.detach(updatedDealUser);
        updatedDealUser
            .gender(UPDATED_GENDER)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .birthDay(UPDATED_BIRTH_DAY)
            .comment(UPDATED_COMMENT)
            .notification(UPDATED_NOTIFICATION)
            .reporting(UPDATED_REPORTING)
            .emailNotification(UPDATED_EMAIL_NOTIFICATION)
            .message(UPDATED_MESSAGE);
        DealUserDTO dealUserDTO = dealUserMapper.toDto(updatedDealUser);

        restDealUserMockMvc.perform(put("/api/deal-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealUserDTO)))
            .andExpect(status().isOk());

        // Validate the DealUser in the database
        List<DealUser> dealUserList = dealUserRepository.findAll();
        assertThat(dealUserList).hasSize(databaseSizeBeforeUpdate);
        DealUser testDealUser = dealUserList.get(dealUserList.size() - 1);
        assertThat(testDealUser.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testDealUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testDealUser.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testDealUser.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testDealUser.getBirthDay()).isEqualTo(UPDATED_BIRTH_DAY);
        assertThat(testDealUser.isComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testDealUser.isNotification()).isEqualTo(UPDATED_NOTIFICATION);
        assertThat(testDealUser.isReporting()).isEqualTo(UPDATED_REPORTING);
        assertThat(testDealUser.isEmailNotification()).isEqualTo(UPDATED_EMAIL_NOTIFICATION);
        assertThat(testDealUser.isMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingDealUser() throws Exception {
        int databaseSizeBeforeUpdate = dealUserRepository.findAll().size();

        // Create the DealUser
        DealUserDTO dealUserDTO = dealUserMapper.toDto(dealUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealUserMockMvc.perform(put("/api/deal-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealUser in the database
        List<DealUser> dealUserList = dealUserRepository.findAll();
        assertThat(dealUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealUser() throws Exception {
        // Initialize the database
        dealUserRepository.saveAndFlush(dealUser);

        int databaseSizeBeforeDelete = dealUserRepository.findAll().size();

        // Delete the dealUser
        restDealUserMockMvc.perform(delete("/api/deal-users/{id}", dealUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealUser> dealUserList = dealUserRepository.findAll();
        assertThat(dealUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
