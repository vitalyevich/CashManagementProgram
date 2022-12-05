package com.cashmanagement.vitalyevich.client.controller;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.firebase.model.WorkTime;
import com.cashmanagement.vitalyevich.client.model.Access;
import com.cashmanagement.vitalyevich.client.model.Company;
import com.cashmanagement.vitalyevich.client.model.Role;
import com.cashmanagement.vitalyevich.client.model.User;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
@Controller
public class UserController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public String users(Model model) {
        model.addAttribute("headerText", "Пользователи");
        //userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "вход на страницу с вкладкой пользователи"));

        Iterable<Access> accesses = userService.getAccesses();

/*        for (Access a : accesses) {
            count++;
        }*/

        Iterable<Role> roles = userService.getRoles();
        Iterable<Company> companies = userService.getCompany();

/*        if (users == null || roles == null) {
            return "error/502";
        }*/

        int size = IterableUtils.size(accesses);
        model.addAttribute("accesses", accesses);
        model.addAttribute("roles",roles);
        model.addAttribute("companies",companies);
        model.addAttribute("size", size);

        return "users"; //error/502
    }

    @GetMapping("/registration")
    public String registration(Model model) {

        return "redirect:/users#blackout-registration"; //error/502
    }

    @PostMapping("/registration")
    public String addUser(User userForm,Access accessForm, Model model, @RequestParam(name = "role") int roleId) { //Access accessForm
    //      Access userFromDb = accessRepository.findByPhone(accessForm.getPhone());

       /* if (userFromDb != null) {
            rm.addFlashAttribute("message", "Данный номер телефона уже используется!");

            return "redirect:/menu/rolls#blackout-registration";
        }

        if (!accessForm.getPassword().equals(accessForm.getConfirmPassword())) {
            return "redirect:/menu/rolls#blackout-registration";
        }*/

/*
        accessForm.setActive(true);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1));
        accessForm.setRoles(roles);
*/

        User user = userService.saveUser(userForm, roleId);

        accessForm.setUser(user);
        accessForm.setActive(false);

        userService.saveAccess(accessForm, user.getId()); // count++ user.getId()

        //
      /*  accessForm.setId(userForm.getId());
        accessForm.setUser(new User(userForm.getId()));
        accessRepository.save(accessForm);

        userPointRepository.save(new UserPoint(userForm.getId(),0));*/
//
        userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "добавление нового пользователя №" + userForm.getId() +""));
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer rowId, Model model, RedirectAttributes rm) {


        //User user = userService.getUser(rowId);

        Access access = userService.getAccess(rowId);


        rm.addFlashAttribute("id", access.getUser().getId());
        rm.addFlashAttribute("login", access.getLogin());
        rm.addFlashAttribute("password", access.getUserPassword());
        rm.addFlashAttribute("confirmPassword", access.getUserPassword());
        rm.addFlashAttribute("email", access.getUser().getEmail());
        rm.addFlashAttribute("role", access.getUser().getRoles());
        rm.addFlashAttribute("active", access.getActive());
        /*rm.addFlashAttribute("company", access.getUser().getRoles());*/
        rm.addFlashAttribute("firstName", access.getUser().getFirstName());
        rm.addFlashAttribute("lastName", access.getUser().getLastName());
        rm.addFlashAttribute("phone", access.getUser().getPhone());

        return "redirect:/users#blackout-edit"; //error/502
    }

    @PostMapping("/edit")
    public String edit(User userForm,Access accessForm, Model model, @RequestParam(name = "role") int roleId) {

        accessForm.setUser(userForm);
        accessForm.setActive(false);

        userService.updateUser(userForm, roleId);
        userService.updateAccess(accessForm, userForm.getId());

        userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "редактирование пользователя №" + userForm.getId()));

        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer rowId, Model model) {
        userService.deleteUser(rowId);

        userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "удаление пользователя №" + rowId +""));

        return "redirect:/users";
    }
}
