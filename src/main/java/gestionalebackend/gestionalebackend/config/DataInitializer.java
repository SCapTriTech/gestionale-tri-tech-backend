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
            // Controlla se i dati sono già presenti
            if (permissionRepository.count() == 0 && roleRepository.count() == 0) {
                
                // Crea i permessi
                Permission viewOwnProfile = createPermission("VIEW_OWN_PROFILE", "Visualizzare il proprio profilo");
                Permission editOwnProfile = createPermission("EDIT_OWN_PROFILE", "Modificare il proprio profilo");
                Permission viewAllProfiles = createPermission("VIEW_ALL_PROFILES", "Visualizzare tutti i profili");
                Permission editTeamProfiles = createPermission("EDIT_TEAM_PROFILES", "Modificare i profili del proprio team");
                Permission editAllProfiles = createPermission("EDIT_ALL_PROFILES", "Modificare tutti i profili");
                Permission manageProjects = createPermission("MANAGE_PROJECTS", "Gestire i progetti");
                Permission manageWorklogs = createPermission("MANAGE_WORKLOGS", "Gestire i worklogs");
                Permission manageTeams = createPermission("MANAGE_TEAMS", "Gestire i team");
                Permission manageRoles = createPermission("MANAGE_ROLES", "Gestire ruoli e permessi");
                Permission viewReports = createPermission("VIEW_REPORTS", "Visualizzare i report");
                Permission generateReports = createPermission("GENERATE_REPORTS", "Generare report");

                // Crea il ruolo DIPENDENTE
                Role dipendente = Role.builder()
                        .name("DIPENDENTE")
                        .description("Dipendente standard")
                        .permissions(new HashSet<>(Arrays.asList(
                                viewOwnProfile, editOwnProfile, viewAllProfiles
                        )))
                        .build();
                roleRepository.save(dipendente);

                // Crea il ruolo TEAM_COUNSELOR
                Role teamCounselor = Role.builder()
                        .name("TEAM_COUNSELOR")
                        .description("Team Counselor")
                        .permissions(new HashSet<>(Arrays.asList(
                                viewOwnProfile, editOwnProfile, viewAllProfiles,
                                editTeamProfiles, manageTeams, viewReports
                        )))
                        .build();
                roleRepository.save(teamCounselor);

                // Crea il ruolo AMMINISTRAZIONE con TUTTI i permessi
                Set<Permission> allPermissions = new HashSet<>(permissionRepository.findAll());
                Role amministrazione = Role.builder()
                        .name("AMMINISTRAZIONE")
                        .description("Amministrazione")
                        .permissions(allPermissions)
                        .build();
                roleRepository.save(amministrazione);

                System.out.println("Database inizializzato con ruoli e permessi di base!");
            } else {
                System.out.println("Database già inizializzato, skip dell'inizializzazione.");
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