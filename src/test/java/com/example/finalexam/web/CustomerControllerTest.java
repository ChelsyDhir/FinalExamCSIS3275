package com.example.finalexam.web;


import com.example.finalexam.entities.Customer;
import com.example.finalexam.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration

// Junit mokito test class: testing deleting, displaying and adding the record

class CustomerControllerTest {
    Customer customer;

    @Autowired
    private MockMvc mockMvc;
    @Mock
    CustomerRepository customerRepository;


    @Mock
    View mockView;


    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setup() throws ParseException {

        customer = new Customer();
        customer.setId(1L);
        customer.setName("John");
        customer.setSeatno("C1");
        customer.setTdate(new Date());

        MockitoAnnotations.openMocks(this);


        mockMvc = standaloneSetup(customerController).setSingleView(mockView).build();

    }

    @Test
    public void findAll_ListView() throws Exception {

        List<Customer> list = new ArrayList<Customer>();
        list.add(customer);
        list.add(customer);

        when(customerRepository.findAll()).thenReturn(list);
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listCustomers", list))
                .andExpect(view().name("customers"))
                .andExpect(model().attribute("listCustomers", hasSize(2)));

        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void delete() {


        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);

        doNothing().when(customerRepository).deleteById(idCapture.capture());

        customerRepository.deleteById(1L);

        assertEquals(1L, idCapture.getValue());

        verify(customerRepository, times(1)).deleteById(1L);

    }
    @Test
    void formCustomers() throws Exception {


        mockMvc.perform(get("/formCustomers"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("customer", new Customer()))
                .andExpect(view().name("formCustomers"));

    }

    @Test
    void save() throws Exception {


        when(customerRepository.save(customer)).thenReturn(customer);

        customerRepository.save(customer);

        verify(customerRepository, times(1)).save(customer);



    }
}