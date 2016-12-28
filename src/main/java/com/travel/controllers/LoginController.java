package com.travel.controllers;

import com.travel.service.ClientService;
import com.travel.model.User;
import com.travel.model.UserProfile;
import com.travel.service.UserProfileService;
import com.travel.service.UserService;
import com.travel.service.TourService;
import com.travel.service.OrderService;
import com.travel.utility.ReturnUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
/**
 * Контролер страниц логина и админ. панели
 *
 * @author Artem Faenko
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private ReturnUserName returnUserName = new ReturnUserName();

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    OrderService orderService;
    @Autowired
    ClientService clientService;
    @Autowired
    TourService tourService;

    @RequestMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        model.addAttribute("orders", orderService.listOrderNumber());
        model.addAttribute("orderReport", orderService.grafikWeekOrders());
        model.addAttribute("orderReportSum", orderService.repotsWeekOrders());
        model.addAttribute("clients", clientService.listClientNumber());
        model.addAttribute("tours", tourService.listTourNumber());
        return "index";
    }

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        return "login/admin";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/admin/newuser" }, method = RequestMethod.GET)
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        return "login/adminregistration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/admin/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, Locale locale, Model model) {

        if (result.hasErrors()) {
            return "login/adminregistration";
        }

        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError = new FieldError("user","ssoId",messageSource.getMessage("admin.error.ssoId", new String[]{user.getSsoId()}, locale));
            result.addError(ssoError);
            return "login/adminregistration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " " + messageSource.getMessage("admin.newuser", null, locale));
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        //return "success";
        return "login/adminregistrationsuccess";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/admin/edit-user-{ssoId}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, Model model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        return "login/adminregistration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/admin/edit-user-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             Model model, @PathVariable String ssoId, Locale locale) {

        if (result.hasErrors()) {
            return "login/adminregistration";
        }
        userService.updateUser(user);
        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " " + messageSource.getMessage("admin.edit", null, locale));
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        return "login/adminregistrationsuccess";
    }


    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/admin/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/admin";
    }


    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @RequestMapping(value = "/getTags", method = RequestMethod.GET)
    @ResponseBody
    public List<UserProfile> initializeProfiles2() {
        return userProfileService.findAll();
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDeniedPage(Model model) {
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        return "login/accessdenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout, Locale locale) {
        ModelAndView model = new ModelAndView();
        if (isCurrentAuthenticationAnonymous()) {
            model.addObject("title", messageSource.getMessage("login.title", null, locale));
            model.addObject("message", messageSource.getMessage("login.message", null, locale));
            model.setViewName("login");
            if (error != null) {
                model.addObject("error", messageSource.getMessage("login.error", null, locale));
            }
            if (logout != null) {
                model.addObject("msg", messageSource.getMessage("login.msg", null, locale));
            }
            return model;
        } else {
            model.setViewName("redirect:/index");
            return model;
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }


}
