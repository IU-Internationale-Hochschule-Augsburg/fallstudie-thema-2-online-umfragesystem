// LOGIN TEST: Note that the login does not require specific values. Testing breadth is chosen over testing depth to increase coverage.
// Hence, we simply differentiate between "good" and "bad" cases.

// zu testen: LoginController, ParticipantSurveyController, QuestionController, QuestionService, SurveyController, SurveyService, UserService, WebSecurityConfig

package com.surveymaster.tests;
import com.surveymaster.*;
import com.surveymaster.entity.User;
import com.surveymaster.repository.AnswerRepository;
import com.surveymaster.repository.QuestionRepository;
import com.surveymaster.repository.SurveyRepository;
import com.surveymaster.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SurveyService surveyService;

    @Mock
    private Model model;

    @InjectMocks
    private LoginController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadLoginScreen() {
        surveyService = new SurveyService(surveyRepository, questionRepository, answerRepository, userRepository);

        LoginController controller = new LoginController(userRepository, surveyService);
        Model model = Mockito.mock(Model.class);
        String viewName = controller.loadLoginScreen(model);

        assertEquals("loginScreen", viewName, "Should return the correct view name");
        assertNotEquals("logoutScreen", viewName, "Should return false because loginScreen is the correct view name");
        verify(model).addAttribute(any(LoginForm.class));
    }

    @Test
    void testLoadRegistration() {

        LoginController controller = new LoginController(userRepository, surveyService);
        Model model = Mockito.mock(Model.class);
        String viewName = controller.loadRegistration(model);

        assertEquals("registerScreen", viewName, "Should return the correct view name");
        assertNotEquals("logoutScreen", viewName, "Should return false because registerScreen is the correct view name");
        verify(model).addAttribute(any(RegisterForm.class));
    }

    @Test
    void testRegisterNewUser_Success() {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setUsername("TSiewert");
        registerForm.setFirstname("Timon");
        registerForm.setSurname("Siewert");
        registerForm.setEmail("timon.siewert@gmx.com");
        registerForm.setPassword("password");
        registerForm.setConfirmPassword("password");

        when(userRepository.findByUsername("TSiewert")).thenReturn(null);
        when(userRepository.findByEmail("timon.siewert@gmx.com")).thenReturn(null);

        String viewName = controller.registerNewUser(model, registerForm);

        assertEquals("redirect:/login", viewName);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testRegisterNewUser_UsernameOrEmailExists() {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setUsername("LHehl");
        registerForm.setEmail("leon.hehl@gmx.com");

        when(userRepository.findByUsername("LHehl")).thenReturn(new User());
        when(userRepository.findByEmail("leon.hehl@gmx.com")).thenReturn(null);

        String viewName = controller.registerNewUser(model, registerForm);

        assertEquals("filledRegisterScreen", viewName);
        verify(model).addAttribute("errorMessage", "Benutzername oder E-Mail ist schon vergeben!");
    }

    @Test
    void testRegisterNewUser_PasswordsDoNotMatch() {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setUsername("alina.maier");
        registerForm.setEmail("alina.maier@gmx.com");
        registerForm.setPassword("password");
        registerForm.setConfirmPassword("differentpassword");

        when(userRepository.findByUsername("alina.maier")).thenReturn(null);
        when(userRepository.findByEmail("alina.maier@egmx.com")).thenReturn(null);

        String viewName = controller.registerNewUser(model, registerForm);

        assertEquals("filledRegisterScreen", viewName);
        verify(model).addAttribute("errorMessage", "Die Passwörter stimmen nicht überein!");
    }



    @Test
    void testHandleUserActions_Logout() {
        String userActions = "logout,402";

        String viewName = controller.handleUserActions(userActions, model);

        assertEquals("redirect:/logout", viewName);
        verify(model).addAttribute(userActions);
    }

    @Test
    void testHandleUserActions_Settings() {
        String userActions = "settings,302";
        User currUser = new User();
        currUser.setUsername("t.siewert");
        currUser.setFirstname("Timon");
        currUser.setSurname("Siewert");
        currUser.setEmail("timon.siewert@gmx.com");

        when(surveyService.getCurrentUser()).thenReturn(currUser);

        String viewName = controller.handleUserActions(userActions, model);

        assertEquals("userSettings", viewName);
        verify(model).addAttribute(eq("userSettingsForm"), any(UserSettingsForm.class));
        verify(model).addAttribute(userActions);
    }

    @Test
    void testHandleUserActions_Default() {
        String userActions = "otherAction,007";

        String viewName = controller.handleUserActions(userActions, model);

        assertEquals("redirect:/survey-admin", viewName);
    }
}