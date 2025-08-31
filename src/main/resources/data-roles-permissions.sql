-- Inserimento permessi di base
INSERT INTO PERMESSI (name, description) VALUES 
('VIEW_OWN_PROFILE', 'Visualizzare il proprio profilo'),
('EDIT_OWN_PROFILE', 'Modificare il proprio profilo'),
('VIEW_ALL_PROFILES', 'Visualizzare tutti i profili'),
('EDIT_TEAM_PROFILES', 'Modificare i profili del proprio team'),
('EDIT_ALL_PROFILES', 'Modificare tutti i profili'),
('MANAGE_PROJECTS', 'Gestire i progetti'),
('MANAGE_WORKLOGS', 'Gestire i worklogs'),
('MANAGE_TEAMS', 'Gestire i team'),
('MANAGE_ROLES', 'Gestire ruoli e permessi'),
('VIEW_REPORTS', 'Visualizzare i report'),
('GENERATE_REPORTS', 'Generare report');

-- Inserimento ruoli di base
INSERT INTO RUOLI (name, description) VALUES 
('DIPENDENTE', 'Dipendente standard'),
('TEAM_COUNSELOR', 'Capo team'),
('AMMINISTRAZIONE', 'Amministrazione');

-- Associazione permessi ai ruoli
-- Dipendente
INSERT INTO RUOLI_PERMESSI (role_id, permission_id) 
SELECT r.id, p.id FROM RUOLI r, PERMESSI p 
WHERE r.name = 'DIPENDENTE' 
AND p.name IN ('VIEW_OWN_PROFILE', 'EDIT_OWN_PROFILE', 'VIEW_ALL_PROFILES');

-- Capo Team
INSERT INTO RUOLI_PERMESSI (role_id, permission_id) 
SELECT r.id, p.id FROM RUOLI r, PERMESSI p 
WHERE r.name = 'TEAM_COUNSELOR'
AND p.name IN ('VIEW_OWN_PROFILE', 'EDIT_OWN_PROFILE', 'VIEW_ALL_PROFILES', 
                'EDIT_TEAM_PROFILES', 'MANAGE_TEAMS', 'VIEW_REPORTS');

-- Amministrazione
INSERT INTO RUOLI_PERMESSI (role_id, permission_id) 
SELECT r.id, p.id FROM RUOLI r, PERMESSI p 
WHERE r.name = 'AMMINISTRAZIONE';