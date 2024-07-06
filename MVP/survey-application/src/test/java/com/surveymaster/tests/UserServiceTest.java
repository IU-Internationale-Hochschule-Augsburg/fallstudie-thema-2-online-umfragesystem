// USER SERVICE TEST: Testing breadth is chosen over testing depth to increase coverage.
// Hence, we simply differentiate between "good" and "bad" cases.

// zu testen: LoginController, ParticipantSurveyController, QuestionController, QuestionService, SurveyController, SurveyService, UserService

package com.surveymaster.tests;

import com.surveymaster.UserService;
import com.surveymaster.entity.User;
import com.surveymaster.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        String username = "testuser";
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword("password");
        when(userRepository.findByUsername(username)).thenReturn(mockUser);


        UserDetails userDetails = userService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("USER")));

        verify(userRepository, times(1)).findByUsername(username);
    }



    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonexistentuser";
        when(userRepository.findByUsername(username)).thenReturn(null);
        assertEquals(null , userRepository.findByUsername(username));
    }
}