package bg.rentacar.service;

import bg.rentacar.exception.ObjectNotFound;
import bg.rentacar.model.dto.AllUsersInfoDTO;
import bg.rentacar.model.dto.UserLoginDTO;
import bg.rentacar.model.dto.UserRegisterDTO;
import bg.rentacar.model.entity.User;
import bg.rentacar.model.entity.UserRole;
import bg.rentacar.model.enums.Role;
import bg.rentacar.repository.UserRepository;
import bg.rentacar.repository.UserRoleRepository;
import bg.rentacar.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserRoleRepository mockUserRoleRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {

        toTest = new UserServiceImpl(
                mockUserRepository,
                mockUserRoleRepository,
                new ModelMapper(),
                mockPasswordEncoder
        );
    }

    @Test
    void testUserRegister(){
        UserRegisterDTO userDTO = new UserRegisterDTO();
        userDTO.setUsername("username");
        userDTO.setPassword("password");
        userDTO.setFirstName("firstName");
        userDTO.setLastName("lastName");
        userDTO.setEmail("some@email");
        userDTO.setPhoneNumber("1234567890");
        userDTO.setAge(22);

        when(mockPasswordEncoder.encode(userDTO.getPassword())).thenReturn("encodedpassword");
        UserRole role = new UserRole();
        when(mockUserRoleRepository.findByRole(Role.USER)).thenReturn(Optional.of(role));

        toTest.register(userDTO);

        verify(mockUserRepository, times(1)).save(userArgumentCaptor.capture());

        User user = userArgumentCaptor.getValue();
        Assertions.assertEquals(userDTO.getUsername(), user.getUsername());
        Assertions.assertEquals(userDTO.getFirstName(), user.getFirstName());
        Assertions.assertEquals(userDTO.getLastName(), user.getLastName());
        Assertions.assertEquals(userDTO.getAge(), user.getAge());
        Assertions.assertEquals(userDTO.getPhoneNumber(), user.getPhoneNumber());
        Assertions.assertEquals(userDTO.getEmail(), user.getEmail());
        Assertions.assertEquals("encoded" + userDTO.getPassword(), user.getPassword());
    }

    @Test
    void testValidateLogin_True(){
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("username");
        userLoginDTO.setPassword("password");
        User user = new User();
        user.setPassword("encodedPassword");
        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(mockPasswordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        Assertions.assertTrue(toTest.validateLogin(userLoginDTO));
    }

    @Test
    void TestValidateLogin_False(){
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("username");
        userLoginDTO.setPassword("password");

        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.empty());

        Assertions.assertFalse(toTest.validateLogin(userLoginDTO));
    }

    @Test
    void testGetUserByName(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("some@email");
        user.setPhoneNumber("1234567890");
        user.setAge(22);

        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.of(user));

        User result = toTest.getUserByName("username");

        Assertions.assertEquals(user, result);
    }

    @Test
    void testGetUserByName_ThrowsException(){
        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFound.class, () -> toTest.getUserByName("username"));
    }

    @Test
    void initUserRole() {
        when(mockUserRoleRepository.count()).thenReturn(0L);

        toTest.initUserRole();

        verify(mockUserRoleRepository, times(Role.values().length)).save(any(UserRole.class));
    }

    @Test
    void getAllUserInfo() {
        User user = new User();
        UserRole role = new UserRole();
        role.setRole(Role.USER);
        user.setRoles(List.of(role));
        when(mockUserRepository.findAll()).thenReturn(List.of(user));

        AllUsersInfoDTO result = toTest.getAllUserInfo();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getUsersInfoDTO().size());
    }

    @Test
    void initAdmin() {
        UserRole role = new UserRole();
        when(mockUserRoleRepository.findByRole(Role.ADMIN)).thenReturn(Optional.of(role));

        when(mockUserRepository.count()).thenReturn(0L);

        toTest.initAdmin();

        verify(mockUserRepository).save(userArgumentCaptor.capture());
        User admin = userArgumentCaptor.getValue();
        Assertions.assertEquals("ADMIN", admin.getRoles().getFirst().getRole().name());
        Assertions.assertEquals("admin@rentacar.com", admin.getEmail());
        Assertions.assertEquals(37, admin.getAge());
        Assertions.assertEquals("Anton", admin.getFirstName());
    }

}
