package com.cashmanagement.vitalyevich.server.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashmanagement.vitalyevich.server.firebase.model.WorkTime;
import com.cashmanagement.vitalyevich.server.model.Access;
import com.cashmanagement.vitalyevich.server.model.Brigade;
import com.cashmanagement.vitalyevich.server.model.Company;
import com.cashmanagement.vitalyevich.server.model.Role;
import com.cashmanagement.vitalyevich.server.model.User;
import com.cashmanagement.vitalyevich.server.repository.AccessRepository;
import com.cashmanagement.vitalyevich.server.repository.BrigadeRepository;
import com.cashmanagement.vitalyevich.server.repository.RoleRepository;
import com.cashmanagement.vitalyevich.server.repository.UserRepository;
import com.cashmanagement.vitalyevich.server.service.FBService;
import com.google.cloud.Timestamp;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private AccessRepository accessRepository;

    @MockBean
    private BrigadeRepository brigadeRepository;

    @MockBean
    private FBService fBService;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserController#users()}
     */
    @Test
    void testUsers() {
        when(this.userRepository.findByOrderByIdAsc()).thenReturn((Iterable<User>) mock(Iterable.class));
        this.userController.users();
        verify(this.userRepository).findByOrderByIdAsc();
    }

    /**
     * Method under test: {@link UserController#createUser(User, Role, Company)}
     */
    @Test
    void testCreateUser() {
        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Role role = new Role();
        role.setId(1);
        role.setRoleName("Role Name");

        Company company = new Company();
        company.setAddress("42 Main St");
        company.setCities(new HashSet<>());
        company.setCompanyName("Company Name");
        company.setHomeNum(10);
        company.setId(1);
        assertSame(user, this.userController.createUser(user1, role, company));
        verify(this.userRepository).save((User) any());
        assertEquals(1, user1.getCompanies().size());
        assertEquals(1, user1.getRoles().size());
    }

    /**
     * Method under test: {@link UserController#updateUser(User, Role, Company)}
     */
    @Test
    void testUpdateUser() {
        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Role role = new Role();
        role.setId(1);
        role.setRoleName("Role Name");

        Company company = new Company();
        company.setAddress("42 Main St");
        company.setCities(new HashSet<>());
        company.setCompanyName("Company Name");
        company.setHomeNum(10);
        company.setId(1);
        assertSame(user, this.userController.updateUser(user1, role, company));
        verify(this.userRepository).save((User) any());
        assertEquals(1, user1.getCompanies().size());
        assertEquals(1, user1.getRoles().size());
    }

    /**
     * Method under test: {@link UserController#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser() {
        doNothing().when(this.userRepository).deleteById((Integer) any());
        this.userController.deleteUser(1);
        verify(this.userRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link UserController#roles()}
     */
    @Test
    void testRoles() {
        ArrayList<Role> roleList = new ArrayList<>();
        when(this.roleRepository.findAll()).thenReturn(roleList);
        Iterable<Role> actualRolesResult = this.userController.roles();
        assertSame(roleList, actualRolesResult);
        assertTrue(((Collection<Role>) actualRolesResult).isEmpty());
        verify(this.roleRepository).findAll();
    }

    /**
     * Method under test: {@link UserController#user(Integer)}
     */
    @Test
    void testUser() {
        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());
        when(this.userRepository.findUserById((Integer) any())).thenReturn(user);
        assertSame(user, this.userController.user(1));
        verify(this.userRepository).findUserById((Integer) any());
    }

    /**
     * Method under test: {@link UserController#access(Integer)}
     */
    @Test
    void testAccess() {
        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        Access access = new Access();
        access.setActive(true);
        access.setId(1);
        access.setLogin("Login");
        access.setUser(user);
        access.setUserPassword("iloveyou");
        Optional<Access> ofResult = Optional.of(access);
        when(this.accessRepository.findAccessByUserId((Integer) any())).thenReturn(ofResult);
        Optional<Access> actualAccessResult = this.userController.access(1);
        assertSame(ofResult, actualAccessResult);
        assertTrue(actualAccessResult.isPresent());
        verify(this.accessRepository).findAccessByUserId((Integer) any());
    }

    /**
     * Method under test: {@link UserController#updateAccess(Access, User)}
     */
    @Test
    void testUpdateAccess() {
        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        Access access = new Access();
        access.setActive(true);
        access.setId(1);
        access.setLogin("Login");
        access.setUser(user);
        access.setUserPassword("iloveyou");
        when(this.accessRepository.save((Access) any())).thenReturn(access);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Access access1 = new Access();
        access1.setActive(true);
        access1.setId(1);
        access1.setLogin("Login");
        access1.setUser(user1);
        access1.setUserPassword("iloveyou");

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());
        assertSame(access, this.userController.updateAccess(access1, user2));
        verify(this.accessRepository).save((Access) any());
        assertSame(user2, access1.getUser());
    }

    /**
     * Method under test: {@link UserController#createAccess(Access, User)}
     */
    @Test
    void testCreateAccess() {
        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        Access access = new Access();
        access.setActive(true);
        access.setId(1);
        access.setLogin("Login");
        access.setUser(user);
        access.setUserPassword("iloveyou");
        when(this.accessRepository.save((Access) any())).thenReturn(access);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Access access1 = new Access();
        access1.setActive(true);
        access1.setId(1);
        access1.setLogin("Login");
        access1.setUser(user1);
        access1.setUserPassword("iloveyou");

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());
        assertSame(access, this.userController.createAccess(access1, user2));
        verify(this.accessRepository).save((Access) any());
        assertSame(user2, access1.getUser());
    }

    /**
     * Method under test: {@link UserController#accesses()}
     */
    @Test
    void testAccesses() {
        when(this.accessRepository.findByOrderByIdAsc()).thenReturn((Iterable<Access>) mock(Iterable.class));
        this.userController.accesses();
        verify(this.accessRepository).findByOrderByIdAsc();
    }

    /**
     * Method under test: {@link UserController#accesses()}
     */
    @Test
    void testAccesses2() {
        when(this.accessRepository.findByOrderByIdAsc()).thenReturn((Iterable<Access>) mock(Iterable.class));
        this.userController.accesses();
        verify(this.accessRepository).findByOrderByIdAsc();
    }

    /**
     * Method under test: {@link UserController#brigades()}
     */
    @Test
    void testBrigades() {
        ArrayList<Brigade> brigadeList = new ArrayList<>();
        when(this.brigadeRepository.findAll()).thenReturn(brigadeList);
        Iterable<Brigade> actualBrigadesResult = this.userController.brigades();
        assertSame(brigadeList, actualBrigadesResult);
        assertTrue(((Collection<Brigade>) actualBrigadesResult).isEmpty());
        verify(this.brigadeRepository).findAll();
    }

    /**
     * Method under test: {@link UserController#createBrigade(Brigade, Company, Set)}
     */
    @Test
    void testCreateBrigade() {
        Company company = new Company();
        company.setAddress("42 Main St");
        company.setCities(new HashSet<>());
        company.setCompanyName("Company Name");
        company.setHomeNum(10);
        company.setId(1);

        Brigade brigade = new Brigade();
        brigade.setActive(true);
        brigade.setBrigadeName("Brigade Name");
        brigade.setCompany(company);
        brigade.setId(1);
        brigade.setUsers(new HashSet<>());
        when(this.brigadeRepository.save((Brigade) any())).thenReturn(brigade);

        Company company1 = new Company();
        company1.setAddress("42 Main St");
        company1.setCities(new HashSet<>());
        company1.setCompanyName("Company Name");
        company1.setHomeNum(10);
        company1.setId(1);

        Brigade brigade1 = new Brigade();
        brigade1.setActive(true);
        brigade1.setBrigadeName("Brigade Name");
        brigade1.setCompany(company1);
        brigade1.setId(1);
        brigade1.setUsers(new HashSet<>());

        Company company2 = new Company();
        company2.setAddress("42 Main St");
        company2.setCities(new HashSet<>());
        company2.setCompanyName("Company Name");
        company2.setHomeNum(10);
        company2.setId(1);
        assertSame(brigade, this.userController.createBrigade(brigade1, company2, new HashSet<>()));
        verify(this.brigadeRepository).save((Brigade) any());
        assertTrue(brigade1.getUsers().isEmpty());
        assertSame(company2, brigade1.getCompany());
    }

    /**
     * Method under test: {@link UserController#createWork(WorkTime)}
     */
    @Test
    void testCreateWork() throws InterruptedException, ExecutionException {
        when(this.fBService.saveWork((WorkTime) any())).thenReturn("Save Work");

        WorkTime workTime = new WorkTime();
        workTime.setDateTime(Timestamp.ofTimeMicroseconds(1L));
        workTime.setDescription("The characteristics of someone or something");
        workTime.setFirstName("Jane");
        workTime.setLastName("Doe");
        this.userController.createWork(workTime);
        verify(this.fBService).saveWork((WorkTime) any());
    }

    /**
     * Method under test: {@link UserController#createWork(WorkTime)}
     */
    @Test
    void testCreateWork2() throws InterruptedException, ExecutionException {
        when(this.fBService.saveWork((WorkTime) any())).thenThrow(new ExecutionException(new Throwable()));

        WorkTime workTime = new WorkTime();
        workTime.setDateTime(Timestamp.ofTimeMicroseconds(1L));
        workTime.setDescription("The characteristics of someone or something");
        workTime.setFirstName("Jane");
        workTime.setLastName("Doe");
        assertThrows(ExecutionException.class, () -> this.userController.createWork(workTime));
        verify(this.fBService).saveWork((WorkTime) any());
    }

    /**
     * Method under test: {@link UserController#deleteBrigade(Integer)}
     */
    @Test
    void testDeleteBrigade() {
        doNothing().when(this.brigadeRepository).deleteById((Integer) any());
        this.userController.deleteBrigade(1);
        verify(this.brigadeRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link UserController#accessByLogin(String)}
     */
    @Test
    void testAccessByLogin() {
        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        Access access = new Access();
        access.setActive(true);
        access.setId(1);
        access.setLogin("Login");
        access.setUser(user);
        access.setUserPassword("iloveyou");
        Optional<Access> ofResult = Optional.of(access);
        when(this.accessRepository.findAccessByLogin((String) any())).thenReturn(ofResult);
        Optional<Access> actualAccessByLoginResult = this.userController.accessByLogin("Login");
        assertSame(ofResult, actualAccessByLoginResult);
        assertTrue(actualAccessByLoginResult.isPresent());
        verify(this.accessRepository).findAccessByLogin((String) any());
    }
}

