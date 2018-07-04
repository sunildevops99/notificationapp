/**
 * 
 */
package com.learnkarts.notificationapp.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkarts.notificationapp.config.NotificationWebConfig;
import com.learnkarts.notificationapp.controller.DeviceController;
import com.learnkarts.notificationapp.filter.CORSFilter;
import com.learnkarts.notificationapp.model.Device;
import com.learnkarts.notificationapp.service.DeviceService;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author BGH19927
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NotificationWebConfig.class})
public class DeviceControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(deviceController)
                .addFilters(new CORSFilter())
                .build();
    }

    // =========================================== Get All Users ==========================================

    @Test
    public void test_get_all_success() throws Exception {
        List<Device> devices = Arrays.asList(
                new Device(1, "ac:00:00:01"),
                new Device(2, "ac:00:00:02"));

        when(deviceService.getAll()).thenReturn(devices);

        mockMvc.perform(get("/devices"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].deviceId", is(1)))
                .andExpect(jsonPath("$[0].deviceName", is("ac:00:00:01")))
                .andExpect(jsonPath("$[1].deviceId", is(2)))
                .andExpect(jsonPath("$[1].deviceName", is("ac:00:00:02")));

    }

    // =========================================== Get User By ID =========================================

    @Test
    public void test_get_by_id_success() throws Exception {
        Device device = new Device(1, "ac:00:00:01");

        when(deviceService.findById(1)).thenReturn(device);

        mockMvc.perform(get("/devices/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.deviceId", is(1)))
                .andExpect(jsonPath("$.deviceName", is("ac:00:00:01")));

    }

    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {
        when(deviceService.findById(1)).thenReturn(null);

        mockMvc.perform(get("/devices/{id}", 1))
                .andExpect(status().isNotFound());

    }

  
 
    // =========================================== CORS Headers ===========================================

    @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/devices"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
