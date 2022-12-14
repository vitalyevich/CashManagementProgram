package com.cashmanagement.vitalyevich.client.controller.users;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.firebase.model.WorkTime;
import com.cashmanagement.vitalyevich.client.model.*;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping("")
    public String users(Model model) {
        model.addAttribute("headerText", "Пользователи");
        model.addAttribute("headerPost", "Руководитель " + seance.getUser().getFirstName());
        //userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "вход на страницу с вкладкой пользователи"));

        Iterable<Access> accesses = userService.getAccesses();
        Iterable<Role> roles = userService.getRoles();
        Iterable<Company> companies = companyService.getCompany();

        Iterable<Country> countries = companyService.getCountries();
        Iterable<City> cities = companyService.getCities();

        int size = IterableUtils.size(accesses);
        model.addAttribute("countries", countries);
        model.addAttribute("cities", cities);
        model.addAttribute("accesses", accesses);
        model.addAttribute("roles",roles);
        model.addAttribute("companies",companies);
        model.addAttribute("size", size);

        return "users"; //error/502
    }

    @GetMapping("/registration")
    public String registration(Model model) {

        return "redirect:/users#blackout-registration";
    }

    @GetMapping("/registration-company")
    public String company(Model model) {

        return "redirect:/users#blackout-company";
    }

    @PostMapping("/registration-company")
    public String registrationCompany(Company company, Model model,
                                      @RequestParam(name = "cityId") int cityId) {

        companyService.saveCompany(company, cityId);
        return "/users";
    }

    @PostMapping("/registration")
    public String addUser(User userForm,Access accessForm, Model model, @RequestParam(name = "role") int roleId,
                          @RequestParam(name = "company") int companyId) {
    //  Access userFromDb = accessRepository.findByPhone(accessForm.getPhone());

       /* if (userFromDb != null) {
            rm.addFlashAttribute("message", "Данный номер телефона уже используется!");
            return "redirect:/menu/rolls#blackout-registration";
        }
        if (!accessForm.getPassword().equals(accessForm.getConfirmPassword())) {
            return "redirect:/menu/rolls#blackout-registration";
        }*/


        User user = userService.saveUser(userForm, roleId, companyId);

        accessForm.setUser(user);

        userService.saveAccess(accessForm, user.getId());

        //userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "добавление нового пользователя №" + userForm.getId() +""));
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer rowId, Model model, RedirectAttributes rm) {

        Access access = userService.getAccess(rowId);

        rm.addFlashAttribute("userId", access.getUser().getId());
        rm.addFlashAttribute("accessId", access.getId());
        rm.addFlashAttribute("login", access.getLogin());
        rm.addFlashAttribute("userPassword", access.getUserPassword());
        rm.addFlashAttribute("confirmPassword", access.getUserPassword());
        rm.addFlashAttribute("email", access.getUser().getEmail());

        rm.addFlashAttribute("role", access.getUser().getRoles().iterator().next().getId());
        rm.addFlashAttribute("company", access.getUser().getCompanies().iterator().next().getId());


        if (access.getActive().equals(true)) {
            rm.addFlashAttribute("active", "checked");
        }

        rm.addFlashAttribute("firstName", access.getUser().getFirstName());
        rm.addFlashAttribute("lastName", access.getUser().getLastName());
        rm.addFlashAttribute("phone", access.getUser().getPhone());

        return "redirect:/users#blackout-edit"; //error/502
    }

    @PostMapping("/edit")
    public String edit(User userForm,Access accessForm, Model model, @RequestParam(name = "role") int roleId, @RequestParam(name = "company") int companyId,
                       @RequestParam(name = "userId") int userId, @RequestParam(name = "accessId") int accessId) {

        userForm.setId(userId);
        accessForm.setUser(userForm);
        accessForm.setId(accessId);
        userService.updateUser(userForm, roleId, companyId);
        userService.updateAccess(accessForm, userForm.getId());

        //userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "редактирование пользователя №" + userForm.getId()));
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer rowId, Model model) {
        userService.deleteUser(rowId);

        //userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "удаление пользователя №" + rowId +""));
        return "redirect:/users";
    }
}
