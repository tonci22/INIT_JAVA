package com.example.INIT_JAVA.services.Implementation;


import com.example.INIT_JAVA.DTOs.request.UserLoginRequestDto;
import com.example.INIT_JAVA.DTOs.request.UserRequestDto;
import com.example.INIT_JAVA.domain.Category;
import com.example.INIT_JAVA.domain.Role;
import com.example.INIT_JAVA.domain.User;
import com.example.INIT_JAVA.enums.RoleType;
import com.example.INIT_JAVA.mappers.RoleMapper;
import com.example.INIT_JAVA.mappers.UserMapper;
import com.example.INIT_JAVA.repositories.CategoryRepository;
import com.example.INIT_JAVA.repositories.RoleRepository;
import com.example.INIT_JAVA.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsServiceImpl")
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = userRepository.findByName(name);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username:" + name);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(List.of(user.getRole())));
    }

    public UserRequestDto save(UserLoginRequestDto userLoginRequestDto) {
        UserRequestDto user = new UserRequestDto();
        user.setName(userLoginRequestDto.getUsername());
        user.setEnabled(true);
        user.setPassword(BCrypt.hashpw(userLoginRequestDto.getPassword(), BCrypt.gensalt()));
        user.setRole(roleMapper.mapToDto(roleRepository.findByName(RoleType.ROLE_USER.toString())));
        userRepository.save(userMapper.mapToDto(user));

        return user;
    }

    public void initAdmin() {

        if (userRepository.findByName("admin") != null)
            return;

        UserRequestDto user = new UserRequestDto();

        user.setName("admin");
        user.setEnabled(true);
        user.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        user.setRole(roleMapper.mapToDto(roleRepository.findByName(RoleType.ROLE_ADMIN.toString())));
        userRepository.save(userMapper.mapToDto(user));
    }

    public void initUser() {

        if (userRepository.findByName("user") != null)
            return;

        UserRequestDto user = new UserRequestDto();

        user.setEnabled(true);
        user.setName("user");
        user.setPassword(BCrypt.hashpw("user", BCrypt.gensalt()));
        user.setRole(roleMapper.mapToDto(roleRepository.findByName(RoleType.ROLE_USER.toString())));
        userRepository.save(userMapper.mapToDto(user));
    }

    public void initCategory() {
        if (categoryRepository.findByNameIn(List.of("Comedy")).size() != 0)
            return;

        Category category = new Category();
        category.setName("Comedy");
        categoryRepository.save(category);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getRoleNames(roles));
    }

    private List<String> getRoleNames(Collection<Role> roles) {

        List<String> roleNames;

        roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());

        return roleNames;
    }

    public List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {

        List<GrantedAuthority> authorities;

        authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return authorities;
    }
}
