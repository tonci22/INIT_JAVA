package com.example.INIT_JAVA.domain;

import com.example.INIT_JAVA.enums.RoleType;
import com.example.INIT_JAVA.repositories.CategoryRepository;
import com.example.INIT_JAVA.repositories.RoleRepository;
import com.example.INIT_JAVA.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRespository;

    public SetupDataLoader(final RoleRepository roleRepository, final UserRepository userRepository,
                           final CategoryRepository categoryRespository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRespository = categoryRespository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createRoleIfNotFound(RoleType.ADMIN.toString());
        createRoleIfNotFound(RoleType.USER.toString());

        User admin = new User();
        admin.setId(1L);
        admin.setName("ivan.ivan@gmail.com");
        admin.setRole(roleRepository.findByName(RoleType.ADMIN.toString()));
        userRepository.save(admin);

        User user = new User();
        user.setId(2L);
        user.setName("marko.marko@hotmail.com");
        user.setRole(roleRepository.findByName(RoleType.USER.toString()));
        userRepository.save(user);

        Category category = new Category();
        category.setId(1L);
        category.setName("Comedy");
        categoryRespository.save(category);

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
