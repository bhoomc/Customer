package com.digitalacademy.customer.controller;

import com.digitalacademy.customer.customer.CustomerSupportTest;
import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.service.CustomerService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerControllerTest {
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
        mvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @DisplayName("Test get customer by Id equals 6")
    @Test
    void testGetCustomerInfoByIdEquals6() throws Exception {

        Customer customerModel = CustomerSupportTest.getCreatedCustomer(); // This model has the Id == 6
        Long reqParam = customerModel.getId();

        when(customerService.getCustomerById(reqParam)).thenReturn(customerModel);
        MvcResult mvcResult = mvc.perform(get("/customer/" + reqParam)).andExpect( status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());


        assertEquals(6,result.get("id"));
        assertEquals("New",result.get("first_name"));
        assertEquals("NewNew",result.get("last_name"));
        assertEquals("new@new.com",result.get("email"));
        assertEquals("66818884477", result.get("phoneNo"));
        assertEquals(10, result.get("age"));

        System.out.println( "************************************" + result + "******************************");

    }
}
