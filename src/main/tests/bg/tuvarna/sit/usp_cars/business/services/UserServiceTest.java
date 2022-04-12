package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.User;
import bg.tuvarna.sit.usp_cars.data.repositories.UserRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.UserModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;
    private User user;
    private UserModel userModel;

    @BeforeEach
    void setUp() {
        userService=UserService.getInstance();
        userRepository=UserRepository.getInstance();
        user=new User("1","1");
        userModel=new UserModel("1","1");
    }

    @Test
    void getAllUsers() {
        ObservableList<UserModel> all=userService.getAllUsers();
        userRepository.save(user);
        ObservableList<UserModel> after=userService.getAllUsers();
        assertNotEquals(all,after);
        userRepository.delete(user);
    }

    @Test
    void findUser() {
        assertNull(userService.findUser(userModel));
    }

    @Test
    void logIn() {
        assertFalse(userService.logIn(userModel));
    }

    @Test
    void registerNewUser() {
        assertTrue(userService.registerNewUser(userModel));
        User user=userService.findUser(userModel);
        userRepository.delete(user);
    }

    @Test
    void deleteUser() {
        assertFalse(userService.deleteUser(userModel));
    }

    @Test
    void findUserByUsername() {
        assertNull(userService.findUserByUsername(userModel.getUser_username()));
    }

    @Test
    void updateUser() {
        assertFalse(userService.updateUser(userModel));
    }
}