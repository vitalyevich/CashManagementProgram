package com.cashmanagement.vitalyevich.client.controller;

import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserController#edit(org.springframework.ui.Model)}
     */
    @Test
    void testEdit() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/edit");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("users/edit-user"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("users/edit-user"));
    }

    /**
     * Method under test: {@link UserController#users(org.springframework.ui.Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUsers() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.cashmanagement.vitalyevich.client.service.UserServiceImpl.getUsers()" because "this.userService" is null
        //       at com.cashmanagement.vitalyevich.client.controller.UserController.users(UserController.java:24)
        //   In order to prevent users(Model)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   users(Model).
        //   See https://diff.blue/R013 to resolve this issue.

        UserController userController = new UserController();
        userController.users(new ConcurrentModel());
    }

    /**
     * Method under test: {@link UserController#edit(org.springframework.ui.Model)}
     */
    @Test
    void testEdit2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/edit", "Uri Variables");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("users/edit-user"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("users/edit-user"));
    }

    /**
     * Method under test: {@link UserController#registration(org.springframework.ui.Model)}
     */
    @Test
    void testRegistration() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/registration");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("users/registration-user"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("users/registration-user"));
    }

    /**
     * Method under test: {@link UserController#registration(org.springframework.ui.Model)}
     */
    @Test
    void testRegistration2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/registration");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("users/registration-user"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("users/registration-user"));
    }
}

