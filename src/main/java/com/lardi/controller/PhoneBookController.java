package com.lardi.controller;

import com.lardi.model.Record;
import com.lardi.model.User;
import com.lardi.service.RecordService;
import com.lardi.service.UserService;
import com.lardi.validator.RecordValidator;
import com.lardi.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PhoneBookController {

    private RecordService recordService;
    private UserService userService;
    private UserValidator userValidator;
    private RecordValidator recordValidator;

    @Autowired
    public PhoneBookController(RecordService recordService, UserService userService, UserValidator userValidator, RecordValidator recordValidator) {
        this.recordService = recordService;
        this.userService =userService;
        this.recordValidator = recordValidator;
        this.userValidator=userValidator;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("records", recordService.findAllRecordsCurrentUser());
        model.addAttribute("record", new Record());
        return "index";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String createRecord(@Valid @ModelAttribute Record record, BindingResult bindingResult, Model model) {

        recordValidator.validate(record,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("records", recordService.findAllRecordsCurrentUser());
            return "index";
        }
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        record.setUser(user);
        recordService.save(record);
        return "redirect:/";
    }

    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public String createRecord(@Valid  @ModelAttribute User user, BindingResult bindingResult) {
        userValidator.validate(user,bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(user);
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping (path = "/remove/{id}")
    public String removePerson(@PathVariable("id")Long id){
        recordService.delete(id);
        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model){
        model.addAttribute("records", recordService.findAllRecordsCurrentUser());
        model.addAttribute("record",recordService.findOne(id));
        return "index";
    }

    @RequestMapping(path = "/filteredRecordsCurrentUsers", method = RequestMethod.POST)
    public String filteredRecords(@RequestParam String findText, Model model) {
        model.addAttribute("records", recordService.filteredRecordsCurrentUser(findText));
        return "filteredRecords";
    }
}
