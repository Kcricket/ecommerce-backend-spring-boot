package com.youtube.ecommerce.service;

import com.youtube.ecommerce.configuration.JwtRequestFilter;
import com.youtube.ecommerce.dao.RoleDao;
import com.youtube.ecommerce.dao.UserDao;
import com.youtube.ecommerce.entity.ChangePasswordRequest;
import com.youtube.ecommerce.entity.Role;
import com.youtube.ecommerce.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserDao userDao;
    private UserService userService;
    @Mock
    private RoleDao roleDao;

    @Mock
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
        userService = new UserService(userDao, roleDao, passwordEncoder);
    }


    private User initTestUser(){
        String username = "testuser";
        User user = new User(
                username,
                "Test",
                "User",
                "test@example.com",
                "password123",
                null,
                "1990-01-01");
        return user;
    }
    @Test
    void registerNewUser() {
        //Given
        User user = initTestUser();
        Role userRole = new Role();
        when(roleDao.findById("User")).thenReturn(Optional.of(userRole));

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(user.getUserPassword())).thenReturn(encodedPassword);

        when(userDao.save(user)).thenReturn(user);
        //When
        User result = userService.registerNewUser(user);
        //Then
        assertNotNull(result);
        verify(userDao).save(user);
        assertEquals(user, result);
        assertEquals(user.getUserName(), result.getUserName());
        assertEquals(user.getUserFirstName(), result.getUserFirstName());
        assertEquals(user.getUserLastName(), result.getUserLastName());
        assertEquals(user.getUserEmail(), result.getUserEmail());
        assertEquals(user.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(1, result.getRole().size());
        assertTrue(result.getRole().contains(userRole));
        assertEquals(encodedPassword, result.getUserPassword());

    }
    @Test
    void testChangeUserPasswordIncorrectOldPassword() {
        // Given
        String username = "testuser";
        String oldPassword = "oldpassword";
        String newPassword = "newpassword";
        User user = new User(username, "Test", "User", "test@example.com", oldPassword, null, "1990-01-01");

        when(userDao.findById(username)).thenReturn(Optional.of(user));
        JwtRequestFilter.USERNAME = username;
        // Create a ChangePasswordRequest object with an incorrect old password
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("wrongpassword", newPassword);
        when(passwordEncoder.matches("wrongpassword", user.getUserPassword())).thenReturn(false);

        // When
        User result = userService.changeUserPassword(changePasswordRequest);

        // Then
        assertNull(result);
        verify(userDao, never()).save(any(User.class));
    }
    @Test
    void changeUserPassword() {
        // Given
        String username = "testuser";
        String oldPassword = "oldpassword";
        String newPassword = "newpassword";
        User user = new User(username, "Test", "User", "test@example.com", oldPassword, null, "1990-01-01");

        when(userDao.findById(username)).thenReturn(Optional.of(user));
        JwtRequestFilter.USERNAME = username;

        // Create a ChangePasswordRequest object with the old and new passwords
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(oldPassword, newPassword);
        // Mock the passwordEncoder to return true when the old password is passed in
        when(passwordEncoder.matches(oldPassword, user.getUserPassword())).thenReturn(true);
        // Mock the passwordEncoder to return the new password when it is encoded
        when(passwordEncoder.encode(newPassword)).thenReturn(newPassword);
        // Mock the userDao to return the user when it is saved
        when(userDao.save(user)).thenReturn(user);

        // When
        User result = userService.changeUserPassword(changePasswordRequest);

        // Then
        assertNotNull(result);
        assertEquals(username, result.getUserName());
        assertEquals(newPassword, result.getUserPassword());
        verify(userDao, times(1)).save(user);
    }

    @Test
    void findByUsername() {
        User user = initTestUser();
        //Given
        String username = "testuser";
        //When the userDao is called with the username, return the user "optional" we created above
        when(userDao.findById(username)).thenReturn(Optional.of(user));
        //We need to set the username in JwtRequestFilter so that it can be used in the service
        JwtRequestFilter.USERNAME = username;
        //When
        User result = userService.findByUsername();
        //Then
        Assertions.assertEquals(username, result.getUserName());
    }

    @Test
    void getEncodedPassword() {
        // given
        String password = "password";
        String expectedEncodedPassword = passwordEncoder.encode(password);

        // when
        String actualEncodedPassword = userService.getEncodedPassword(password);

        // then
        assertEquals(expectedEncodedPassword, actualEncodedPassword);
    }
}