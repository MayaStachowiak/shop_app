package com.example.shop.controller;

import com.example.shop.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class UserControllerTest {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }

    @Test
    void shouldSaveUser() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:json/user_create_request_ok.json");
        String requestBody = IOUtils.toString(resource.getInputStream());
        mockMvc.perform(post("/api/v1/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("Zdzisiek"))
                .andExpect(jsonPath("$.firstName").value("Franciszek"))
                .andExpect(jsonPath("$.lastName").value("Frankowski"))
                .andExpect(jsonPath("$.email").value("jadzia@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("587856999"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.confirmPassword").doesNotExist())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void shouldNotSaveUserWhenDataIsIncorrect() throws Exception {

        Resource resource = resourceLoader.getResource("classpath:json/user_create_request_bad_request.json");
        String requestBody = IOUtils.toString(resource.getInputStream());
        mockMvc.perform(post("/api/v1/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].fieldName").value("userName"))
                .andExpect(jsonPath("$[0].message").value("must not be blank"))
                .andExpect(jsonPath("$[1].fieldName").value("phoneNumber"))
                .andExpect(jsonPath("$[1].message").value("length must be between 9 and 9"))
                .andExpect(jsonPath("$[2].fieldName").value("email"))
                .andExpect(jsonPath("$[2].message").value("must be a well-formed email address"))
                .andExpect(jsonPath("$[3].fieldName").value("lastName"))
                .andExpect(jsonPath("$[3].message").value("must not be blank"));
//                .andExpect(jsonPath("$[*].fieldName", containsInAnyOrder("email", "phoneNumber", "userName", "lastName")))
//                .andExpect(jsonPath("$[*].message", containsInAnyOrder("must be a well-formed email address", "must not be blank", "must not be blank",
//                        "length must be between 9 and 9")));
    }


    @Test
    @Sql("classpath:sql/user_data.sql")
    void shouldGetUserPage() throws Exception {
//        User user = new User();
//        user.setUserName("Zdzisio");
//        user.setEmail("Zdzisio@gmail.com");
//        user.setFirstName("Bla bla");
//        user.setLastName("Blaaaa");
//        user.setPassword("123");
//        user.setPhoneNumber("666444555");
//
//        User user2 = new User();
//        user2.setUserName("Zbysio");
//        user2.setEmail("Zdzisio@gmail.com");
//        user2.setFirstName("Bla bla");
//        user2.setLastName("Blaaaa");
//        user2.setPassword("123");
//        user2.setPhoneNumber("666444555");
//
//        User user3 = new User();
//        user3.setUserName("Wiesio");
//        user3.setEmail("Zdzisio@gmail.com");
//        user3.setFirstName("Bla bla");
//        user3.setLastName("Blaaaa");
//        user3.setPassword("123");
//        user3.setPhoneNumber("666444555");

//        userRepository.save(user);
//        userRepository.save(user2);
//        userRepository.save(user3);


        mockMvc.perform(get("/api/v1/users/all")
                        .queryParam("page", "0")
                        .queryParam("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value("3"))
                .andExpect(jsonPath("$.size").value("2"))
                .andExpect(jsonPath("$.content", hasSize(2)));
    }
}