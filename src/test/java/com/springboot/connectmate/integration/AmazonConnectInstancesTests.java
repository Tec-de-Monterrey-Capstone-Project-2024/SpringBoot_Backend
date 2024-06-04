package com.springboot.connectmate.integration;

import com.amazonaws.services.connect.model.Instance;
import com.amazonaws.services.connect.model.InstanceSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.repositories.UserRepository;
import com.springboot.connectmate.services.AmazonConnectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource({
        "classpath:application-db-test.properties",
        "classpath:application-aws-test.properties"
})
public class AmazonConnectInstancesTests {

    @Container
    private static MySQLContainer mysqlContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("test_connectmate_db")
            .withUsername("test_user")
            .withPassword("test_password");

    static {
        mysqlContainer.start();
        System.setProperty("JDBC_URL", mysqlContainer.getJdbcUrl());
        System.setProperty("DB_NAME", "test_connectmate_db");
        System.setProperty("DB_USER", "test_user");
        System.setProperty("DB_PASSWORD", "test_password");
    }


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AmazonConnectService amazonConnectService;

    private List<InstanceSummary> instanceSummaries;
    private Instance instance;

    @BeforeEach
    void setup() {
        instanceSummaries = Arrays.asList(
                new InstanceSummary()
                        .withId("2f899f72-4021-4b35-b490-07bcc2cc27bb")
                        .withArn("arn:aws:connect:us-east-1:674530197385:instance/2f899f72-4021-4b35-b490-07bcc2cc27bb")
                        .withIdentityManagementType("CONNECT_MANAGED")
                        .withInstanceAlias("instance-2")
                        .withCreatedTime(new java.util.Date())
                        .withServiceRole("arn:aws:iam::674530197385:role/aws-service-role/connect.amazonaws.com/AWSServiceRoleForAmazonConnect_VnGO7KOHDK8Lage0JexM")
                        .withInstanceStatus("ACTIVE")
                        .withInboundCallsEnabled(false)
                        .withOutboundCallsEnabled(false)
                        .withInstanceAccessUrl("https://instance-2.my.connect.aws"),
                new InstanceSummary()
                        .withId("7c78bd60-4a9f-40e5-b461-b7a0dfaad848")
                        .withArn("arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848")
                        .withIdentityManagementType("CONNECT_MANAGED")
                        .withInstanceAlias("connectmate")
                        .withCreatedTime(new java.util.Date())
                        .withServiceRole("arn:aws:iam::674530197385:role/aws-service-role/connect.amazonaws.com/AWSServiceRoleForAmazonConnect_9wtZA7Tow0YOaFA4675H")
                        .withInstanceStatus("ACTIVE")
                        .withInboundCallsEnabled(true)
                        .withOutboundCallsEnabled(true)
                        .withInstanceAccessUrl("https://connectmate.my.connect.aws")
        );

        instance = new Instance()
                .withId("2f899f72-4021-4b35-b490-07bcc2cc27bb")
                .withArn("arn:aws:connect:us-east-1:674530197385:instance/2f899f72-4021-4b35-b490-07bcc2cc27bb")
                .withIdentityManagementType("CONNECT_MANAGED")
                .withInstanceAlias("instance-2")
                .withCreatedTime(new java.util.Date())
                .withServiceRole("arn:aws:iam::674530197385:role/aws-service-role/connect.amazonaws.com/AWSServiceRoleForAmazonConnect_VnGO7KOHDK8Lage0JexM")
                .withInstanceStatus("ACTIVE")
                .withInboundCallsEnabled(false)
                .withOutboundCallsEnabled(false)
                .withInstanceAccessUrl("https://instance-2.my.connect.aws");
    }

    @Test
    public void listInstances() throws Exception {
        Mockito.when(amazonConnectService.listConnectInstances()).thenReturn(instanceSummaries);

        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances")
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(instanceSummaries.get(0).getId())))
                .andExpect(jsonPath("$[1].id", is(instanceSummaries.get(1).getId())));
    }

    @Test
    public void getInstance() throws Exception {
        String instanceId = "2f899f72-4021-4b35-b490-07bcc2cc27bb";
        Mockito.when(amazonConnectService.getConnectInstance(instanceId)).thenReturn(instance);

        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/{instanceId}", instanceId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(instance.getId())))
                .andExpect(jsonPath("$.instanceAlias", is(instance.getInstanceAlias())));
    }
}
