package com.andigital.andservice.controller;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.domain.SystemProperties;
import com.andigital.andservice.repository.SystemPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The type Dashboard controller test.
 */
public class DashboardControllerTest extends BaseTestNG {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SystemPropertiesRepository systemPropertiesRepository;

    private static String updatedDate = new Date().toString();
    private final ManualRestDocumentation restDocumentation = new ManualRestDocumentation("build/docs/generated-snippets");

    /**
     * Sets up.
     * @param method the method
     */
    @BeforeMethod
    public void setUp(Method method) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(this.restDocumentation)).build();
        this.restDocumentation.beforeTest(getClass(), method.getName());

        SystemProperties sys = new SystemProperties();
        sys.setLastUpdatedDate(updatedDate);
        // save System Properties, verify has ID value after save
        Assert.assertNull(sys.getId());
        this.systemPropertiesRepository.save(sys);
        Assert.assertNotNull(sys.getId());
       /* MockitoAnnotations.initMocks(this);
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(dashboardController).build();
        //document = documentPrettyPrintReqResp("dashboard");*/
    }

    /**
     * Test dashboard api.
     * @throws Exception the exception
     */
    @Test
    public void testDashboardApi() throws Exception{
        this.mockMvc.perform(get("/dashboard/test").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
                .andDo(print()).andExpect(content().string(containsString("Rest Service is up and running for DashBoard!!")))
                .andDo(document("dashboard"));
    }


    /**
     * Should return success.
     *
     * @throws Exception the exception
     */
/* Test for System properties API Rest Docs documentation */
    @Test
    public void shouldReturnSuccess() throws Exception {
        this.mockMvc.perform(get("/dashboard/systemproperties").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("status",equalTo("success")))
                .andDo(document("dashboard",responseFields(
                        fieldWithPath("status").description("This will give status 'success' for API request."),
                        fieldWithPath("results.systemproperties.id").description("This is MongoDB Id for System Properties."),
                        fieldWithPath("results.systemproperties.lastUpdatedDate").description("This is the System last Updated Date.")
                )));

    }

    /**
     * Should return error.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldReturnError() throws Exception {

        this.systemPropertiesRepository.deleteAll();
        this.mockMvc.perform(get("/dashboard/systemproperties").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("status",equalTo("error")))
                .andDo(document("dashboard",responseFields(
                        fieldWithPath("status").description("This will give status 'error' for API request."),
                        fieldWithPath("results.error.message").description("This is Error Message in case of any error."),
                        fieldWithPath("results.error.code").description("This is Error Code representing the error."),
                        fieldWithPath("results.error.details").description("This is Stack Trace information if applicable.")
                )));

    }

    /**
     * Tear down.
     */
    @AfterMethod
    public void tearDown() {
        this.restDocumentation.afterTest();
    }

}
