
INSERT INTO dmt_permission (id, code, name, description) VALUES
    ('11111111-1111-1111-1111-111111111111', 'READ_USERS', 'Read Users', 'Can view user details'),
    ('22222222-2222-2222-2222-222222222222', 'WRITE_USERS', 'Write Users', 'Can modify user details'),
    ('33333333-3333-3333-3333-333333333333', 'DELETE_USERS', 'Delete Users', 'Can remove users'),
    ('44444444-4444-4444-4444-444444444444', 'READ_ROLES', 'Read Roles', 'Can view roles'),
    ('55555555-5555-5555-5555-555555555555', 'WRITE_ROLES', 'Write Roles', 'Can modify roles');

-- Insert Roles
INSERT INTO dmt_role (id, code, name) VALUES
    ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'ADMIN', 'Administrator'),
    ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'USER', 'Regular User');

-- Ensure role_permission inserts are separate to avoid syntax issues
INSERT INTO dmt_role_permission (role_id, permission_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111');
INSERT INTO dmt_role_permission (role_id, permission_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '22222222-2222-2222-2222-222222222222');
INSERT INTO dmt_role_permission (role_id, permission_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '33333333-3333-3333-3333-333333333333');
INSERT INTO dmt_role_permission (role_id, permission_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '44444444-4444-4444-4444-444444444444');
INSERT INTO dmt_role_permission (role_id, permission_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '55555555-5555-5555-5555-555555555555');
INSERT INTO dmt_role_permission (role_id, permission_id) VALUES ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '11111111-1111-1111-1111-111111111111');

-- Insert Users
INSERT INTO dmt_user (user_id, name, last_name, email, password) VALUES
    ('99999999-9999-9999-9999-999999999999', 'John', 'Doe', 'admin@example.com', '$2a$12$A2FEpT/.ALv5bOLz7mRwJO8Z5RBrN7AFmNiPJVunW6LufpHV37q6a'),
    ('88888888-8888-8888-8888-888888888888', 'Jane', 'Smith', 'user@example.com', '$2a$12$5mwBurSxb3Edmy/VC7mXEeGo/VeLErR35SV8VB.tKlw4u51mEComW');

INSERT INTO dmt_user_role (user_id, role_id) VALUES
    ('99999999-9999-9999-9999-999999999999', 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('88888888-8888-8888-8888-888888888888', 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb');    


-- Disable FK enforcement triggers (superuser only)
ALTER TABLE dmt_user_profile DISABLE TRIGGER ALL;

-- Insert data without constraint errors
INSERT INTO dmt_user_profile (description, user_id, image_id) VALUES 
('Jhon description', '99999999-9999-9999-9999-999999999999', '1'), 
('Jane Description', '88888888-8888-8888-8888-888888888888', '2');

-- Re-enable triggers after insertion
ALTER TABLE dmt_user_profile ENABLE TRIGGER ALL;