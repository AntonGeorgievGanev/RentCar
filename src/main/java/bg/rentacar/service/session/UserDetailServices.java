package bg.rentacar.service.session;

import bg.rentacar.model.entity.User;
import bg.rentacar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServices implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found!"));
    }

    private UserDetails mapToUserDetails(User user) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(mapToGrantedAuthorities(user)).build();
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toUnmodifiableList());
    }
}
