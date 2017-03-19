package com.lardi.controller;


import com.lardi.model.Record;
import com.lardi.service.RecordService;
import com.lardi.service.UserService;
import com.lardi.validator.RecordValidator;
import com.lardi.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by ellik on 18.03.2017.
 */

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringBootApplication(exclude = PhoneBookController.class)
public class PhoneBookControllerTest {

    @Mock
    RecordService recordService;
    @Mock
    UserService userService;
    @Mock
    UserValidator userValidator;
    @Mock
    RecordValidator recordValidator;

    @InjectMocks
    PhoneBookController phoneBookController;

    private MockMvc mockMvc;

    @Before
    public void setup (){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(phoneBookController).build();
    }

    @Test
    public void testFilteredRecords() throws Exception {
        List<Record> recordList = new ArrayList<Record>();
        recordList.add(new Record());
        recordList.add(new Record());
        String findText ="";
        when(recordService.filteredRecordsCurrentUser("")).thenReturn((Iterable) recordList);
        mockMvc.perform(post("/filteredRecords").param("findText", findText))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view ().name("filteredRecords"))
                .andExpect(model().attribute("records",recordList));
    }
    @Test
    public void testIndex() throws Exception {
        List<Record> recordList = new ArrayList<Record>();
        recordList.add(new Record());
        recordList.add(new Record());
        when(recordService.findAllRecordsCurrentUser()).thenReturn((Iterable) recordList);
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view ().name("index"))
                .andExpect(model().attribute("records",recordList));
    }

}