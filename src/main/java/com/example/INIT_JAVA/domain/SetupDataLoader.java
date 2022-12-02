package com.example.INIT_JAVA.domain;

import com.example.INIT_JAVA.enums.RoleType;
import com.example.INIT_JAVA.repositories.RoleRepository;
import com.example.INIT_JAVA.repositories.UserRepository;
import com.example.INIT_JAVA.services.Implementation.UserDetailsServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final UserDetailsServiceImpl userDetailsService;

    public SetupDataLoader(final RoleRepository roleRepository, final UserRepository userRepository,
                           final UserDetailsServiceImpl userDetailsService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createRoleIfNotFound(RoleType.ROLE_ADMIN.toString());
        createRoleIfNotFound(RoleType.ROLE_USER.toString());

        userDetailsService.initAdmin();
        userDetailsService.initUser();
        userDetailsService.initCategory();

        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);

        if (role == null) {
            Role newRole = new Role(name);
            roleRepository.save(newRole);
        }
    }
}
