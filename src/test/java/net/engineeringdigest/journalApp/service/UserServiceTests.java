package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


//    @BeforeEach
//    @AfterEach
//    @BeforeAll
//    @AfterAll


    @Disabled
    @Test
    public void testAdd() {
        assertEquals(4, 2 + 2);
        assertTrue(5 > 3);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void testParams(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Ram",
            "Shyam",
            "ABCD"
    })
    public void testFindByUsername(String username) {
        assertNotNull(userService.findByUsername(username));
    }


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

    @Test
    public void testUserFromDB() {
        User user = userRepository.findByUsername("Ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }
}
