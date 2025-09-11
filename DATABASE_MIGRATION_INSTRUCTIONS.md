# Database Migration Instructions - Italian to English

## ⚠️ IMPORTANT WARNING
This process will DELETE ALL DATA in your database. Make sure to backup any important data before proceeding.

## Steps to Clean and Migrate Database

### 1. Backup Current Data (Optional)
If you have important data, backup your database first:
```bash
pg_dump -U postgres -d postgres > backup_before_migration.sql
```

### 2. Execute the Cleanup Script
Connect to your PostgreSQL database and run the cleanup script:

```bash
psql -U postgres -d postgres -f src/main/resources/clean-and-migrate-database.sql
```

Or if you prefer using a GUI tool like pgAdmin, DBeaver, or IntelliJ IDEA's database tool:
1. Open the file `src/main/resources/clean-and-migrate-database.sql`
2. Execute it against your database

### 3. Update Application Configuration
Temporarily change the Hibernate DDL setting in `application.properties`:

```properties
# Change from:
spring.jpa.hibernate.ddl-auto=update

# To:
spring.jpa.hibernate.ddl-auto=create
```

### 4. Disable the SQL Init Script Temporarily
Comment out these lines in `application.properties`:

```properties
# spring.sql.init.mode=always
# spring.sql.init.data-locations=classpath:update-employees-role.sql
# spring.jpa.defer-datasource-initialization=true
```

### 5. Start the Application
Run the Spring Boot application. It will create all the new tables in English with the correct structure.

```bash
./mvnw spring-boot:run
```

### 6. Verify the Tables
Check that all tables have been created correctly:
- EMPLOYEES (not DIPENDENTI)
- PROJECTS (not PROGETTI)
- OFFICES (not UFFICI)
- ROLES (not RUOLI)
- PERMISSIONS (not PERMESSI)
- WORK_LOGS (not WORKLOGS)
- TECHNOLOGIES (not TECNOLOGIE)
- TECHNOLOGY_CATEGORIES
- All junction tables should also be in English

### 7. Restore Configuration
After verifying everything works:

1. Change back to update mode in `application.properties`:
```properties
spring.jpa.hibernate.ddl-auto=update
```

2. Re-enable the SQL init if needed (uncomment):
```properties
spring.sql.init.mode=always
spring.sql.init.data-locations=classpath:update-employees-role.sql
spring.jpa.defer-datasource-initialization=true
```

### 8. Initial Data
The `DataInitializer` class will automatically create:
- Basic permissions (VIEW_OWN_PROFILE, EDIT_OWN_PROFILE, etc.)
- Three roles: EMPLOYEE, TEAM_COUNSELOR, ADMINISTRATION
- All with English names and descriptions

## Alternative: Keep Existing Data
If you want to migrate existing data instead of starting fresh, you would need to:
1. Create a more complex migration script that copies data from Italian tables to English tables
2. Handle foreign key relationships carefully
3. This is more complex and error-prone

## Troubleshooting

### If you see duplicate columns in OFFICES table:
This happens when Hibernate tries to update existing tables. The clean script above will remove everything and let Hibernate recreate from scratch.

### If you get foreign key constraint errors:
Make sure to run the entire cleanup script, which drops constraints before dropping tables.

### If Spring Boot won't start after migration:
1. Check that all JPQL queries use the new entity names (EMPLOYEES, WORK_LOGS, etc.)
2. Verify that all field names in DTOs match the new English field names
3. Check the logs for specific error messages