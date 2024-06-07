package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@TestPropertySource("classpath:application-db-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest")
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
    private UserRepository userRepository;

    @DisplayName("JUnit Test for UserRepository - Save User")
    @Test
    public void givenUserObject_whenSave_thenReturnSavedUser() {
        // given - precondition or setup
        User user = User.builder()
                .firebaseId("firebaseId")
                .connectId("connectId")
                .instanceId("instanceId")
                .build();

        // when - action or operation
        User savedUser = userRepository.save(user);

        // then - verification or assertion
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getFirebaseId()).isEqualTo(user.getFirebaseId());
        assertThat(savedUser.getConnectId()).isEqualTo(user.getConnectId());
        assertThat(savedUser.getInstanceId()).isEqualTo(user.getInstanceId());
    }

    @DisplayName("JUnit Test for UserRepository - Find User by Firebase ID")
    @Test
    public void givenFireUserId_whenFindById_thenReturnUserObject() {
        // given - precondition or setup
        User user = User.builder()
                .firebaseId("firebaseId")
                .connectId("connectId")
                .instanceId("instanceId")
                .build();
        userRepository.save(user);

        // when - action or operation
        Optional<User> foundUser = userRepository.findById(user.getFirebaseId());

        // then - verification or assertion
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getFirebaseId()).isEqualTo(user.getFirebaseId());
        assertThat(foundUser.get().getConnectId()).isEqualTo(user.getConnectId());
        assertThat(foundUser.get().getInstanceId()).isEqualTo(user.getInstanceId());
    }

    @DisplayName("JUnit Test for UserRepository - Find All Users")
    @Test
    public void whenFindAll_thenReturnUserList() {
        // given - precondition or setup
        User user1 = User.builder()
                .firebaseId("firebaseId1")
                .connectId("connectId1")
                .instanceId("instanceId1")
                .build();
        User user2 = User.builder()
                .firebaseId("firebaseId2")
                .connectId("connectId2")
                .instanceId("instanceId2")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);

        // when - action or operation
        Iterable<User> userList = userRepository.findAll();

        // then - verification or assertion
        assertThat(userList).hasSize(2);
    }

    @DisplayName("JUnit Test for UserRepository - Update User")
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser() {
        // given - precondition or setup
        User user = User.builder()
                .firebaseId("firebaseId")
                .connectId("connectId")
                .instanceId("instanceId")
                .build();
        userRepository.save(user);

        // when - action or operation
        User savedUser = userRepository.findById(user.getFirebaseId()).get();
        savedUser.setFirebaseId("newFirebaseId");
        User updatedUser = userRepository.save(savedUser);

        // then - verification or assertion
        assertThat(updatedUser.getFirebaseId()).isEqualTo("newFirebaseId");
    }

    @DisplayName("JUnit Test for UserRepository - Delete User")
    @Test
    public void givenUserObject_whenDelete_thenRemoveUser() {
        // given - precondition or setup
        User user = User.builder()
                .firebaseId("firebaseId")
                .connectId("connectId")
                .instanceId("instanceId")
                .build();
        userRepository.save(user);

        // when - action or operation
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(user.getFirebaseId());

        // then - verification or assertion
        assertThat(deletedUser).isEmpty();
    }
}
