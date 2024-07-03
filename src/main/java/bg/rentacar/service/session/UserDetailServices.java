package bg.rentacar.service.session;

import bg.rentacar.model.entity.User;
import bg.rentacar.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailServices implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(this::mapToUserDetails).orElseThrow();
    }

    private UserDetails mapToUserDetails(User user) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(mapToGrantedAuthorities(user)).build();
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE" + role.getRole().name()))
                .collect(Collectors.toUnmodifiableList());
    }
}
