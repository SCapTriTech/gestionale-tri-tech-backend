package gestionalebackend.gestionalebackend.config;

import gestionalebackend.gestionalebackend.permission.model.Permission;
import gestionalebackend.gestionalebackend.permission.repository.PermissionRepository;
import gestionalebackend.gestionalebackend.role.model.Role;
import gestionalebackend.gestionalebackend.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Check if data is already present
            if (permissionRepository.count() == 0 && roleRepository.count() == 0) {
                
                // Create permissions
                Permission viewOwnProfile = createPermission("VIEW_OWN_PROFILE", "View own profile");
                Permission editOwnProfile = createPermission("EDIT_OWN_PROFILE", "Edit own profile");
                Permission viewAllProfiles = createPermission("VIEW_ALL_PROFILES", "View all profiles");
                Permission editTeamProfiles = createPermission("EDIT_TEAM_PROFILES", "Edit team profiles");
                Permission editAllProfiles = createPermission("EDIT_ALL_PROFILES", "Edit all profiles");
                Permission manageProjects = createPermission("MANAGE_PROJECTS", "Manage projects");
                Permission manageWorklogs = createPermission("MANAGE_WORKLOGS", "Manage worklogs");
                Permission manageTeams = createPermission("MANAGE_TEAMS", "Manage teams");
                Permission manageRoles = createPermission("MANAGE_ROLES", "Manage roles and permissions");
                Permission viewReports = createPermission("VIEW_REPORTS", "View reports");
                Permission generateReports = createPermission("GENERATE_REPORTS", "Generate reports");

                // Create EMPLOYEE role
                Role employee = Role.builder()
                        .name("EMPLOYEE")
                        .description("Standard employee")
                        .permissions(new HashSet<>(Arrays.asList(
                                viewOwnProfile, editOwnProfile, viewAllProfiles
                        )))
                        .build();
                roleRepository.save(employee);

                // Create TEAM_COUNSELOR role
                Role teamCounselor = Role.builder()
                        .name("TEAM_COUNSELOR")
                        .description("Team Counselor")
                        .permissions(new HashSet<>(Arrays.asList(
                                viewOwnProfile, editOwnProfile, viewAllProfiles,
                                editTeamProfiles, manageTeams, viewReports
                        )))
                        .build();
                roleRepository.save(teamCounselor);

                // Create ADMINISTRATION role with ALL permissions
                Set<Permission> allPermissions = new HashSet<>(permissionRepository.findAll());
                Role administration = Role.builder()
                        .name("ADMINISTRATION")
                        .description("Administration")
                        .permissions(allPermissions)
                        .build();
                roleRepository.save(administration);

                System.out.println("Database initialized with base roles and permissions!");
            } else {
                System.out.println("Database already initialized, skipping initialization.");
            }
        };
    }

    private Permission createPermission(String name, String description) {
        Permission permission = Permission.builder()
                .name(name)
                .description(description)
                .build();
        return permissionRepository.save(permission);
    }
}