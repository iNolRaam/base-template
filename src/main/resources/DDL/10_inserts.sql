-- Insert default types for rights
INSERT INTO base_template.type_rights (id, name, visible, created_by, created_at, updated_by, lst_updated_at) VALUES(1, 'create', true, 'Yomero', '2026-01-01 01:00:00', NULL, NULL);
INSERT INTO base_template.type_rights (id, name, visible, created_by, created_at, updated_by, lst_updated_at) VALUES(2, 'view', true, 'Yomero', '2026-01-01 01:00:00', NULL, NULL);
INSERT INTO base_template.type_rights (id, name, visible, created_by, created_at, updated_by, lst_updated_at) VALUES(3, 'delete', true, 'Yomero', '2026-01-01 01:00:00', NULL, NULL);
INSERT INTO base_template.type_rights (id, name, visible, created_by, created_at, updated_by, lst_updated_at) VALUES(4, 'update', true, 'Yomero', '2026-01-01 01:00:00', NULL, NULL);

ALTER SEQUENCE base_template.seq_type_rights_id RESTART WITH 5;