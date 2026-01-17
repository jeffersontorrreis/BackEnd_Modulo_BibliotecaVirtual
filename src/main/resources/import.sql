INSERT INTO tb_categoria (nome, descricao) VALUES ('Ficção', 'Livros de ficção e fantasia');
INSERT INTO tb_categoria (nome, descricao) VALUES ('Tecnologia', 'Livros sobre programação e tecnologia');
INSERT INTO tb_categoria (nome, descricao) VALUES ('História', 'Livros de história mundial e nacional');

INSERT INTO tb_livro (titulo, autor, isbn, sinopse, quantidade_disponivel, img_url) VALUES ('O Senhor dos Anéis', 'J.R.R. Tolkien', 111111, 'O Senhor dos Anéis narra a épica jornada de Frodo Bolseiro, um hobbit que herda o Um Anel, forjado pelo Senhor Sombrio Sauron, para dominar a Terra-média.', 5, 'https://upload.wikimedia.org/wikipedia/pt/thumb/8/87/Ringstrilogyposter.jpg/250px-Ringstrilogyposter.jpg');
INSERT INTO tb_livro (titulo, autor, isbn, sinopse, quantidade_disponivel, img_url) VALUES ('Clean Code', 'Robert C. Martin', 222222, 'É um guia essencial para programadores escreverem códigos claros, legíveis e fáceis de manter, focando em boas práticas.', 3, 'https://euro-libris.ro/site_img/products/400/2013/10/9780132350884.jpg');
INSERT INTO tb_livro (titulo, autor, isbn, sinopse, quantidade_disponivel, img_url) VALUES ('História do Brasil', 'Boris Fausto', 333333, 'Destaca uma narrativa abrangente e analítica da formação brasileira, desde a colonização portuguesa até os dias atuais.', 4, 'https://pictures.abebooks.com/isbn/9788531405921-us.jpg');

INSERT INTO tb_livro_categoria (livro_id, categoria_id) VALUES (1, 1);
INSERT INTO tb_livro_categoria (livro_id, categoria_id) VALUES (2, 2);
INSERT INTO tb_livro_categoria (livro_id, categoria_id) VALUES (3, 3);


INSERT INTO tb_usuario (name, cpf, email, fone, endereco, password) VALUES ('João Silva', '025.149.460-85', 'joao.silva@email.com', '91-99999-1111', 'Rua das Flores, 123', '$2a$10$IHklSQuHEp4x5xZYD5huQuHhNI5eL5s3CVqGlCZ/.4zsxMMXn6ZKC');
INSERT INTO tb_usuario (name, cpf, email, fone, endereco, password) VALUES ('Maria Oliveira', '249.730.620-65', 'maria.oliveira@email.com', '91-98888-2222', 'Av. Brasil, 456', '$2a$10$uOLipYXNTA/hYaszGxasTexIrXVd29VsLqDYoJQlEn8izSkAzoWVa');
INSERT INTO tb_usuario (name, cpf, email, fone, endereco, password) VALUES ('Jefferson Torres', '0746.640.240-29', 'jefferson.torres@email.com', '91-98129-3333', 'Travessa Pará, 789', '$2a$10$Oem4IjNOSSB5J2ryCT8G1OJlkHIwelQjfvATCP0uhIRZwg2./KBt6');
INSERT INTO tb_usuario (name, cpf, email, fone, endereco, password) VALUES ('Carlos Souza', '886.101.900-56', 'carlos.souza@email.com', '91-97777-3333', 'Travessa Pará, 789', '$2a$10$Oem4IjNOSSB5J2ryCT8G1OJlkHIwelQjfvATCP0uhIRZwg2./KBt6');


INSERT INTO tb_role(authority) VALUES ('ROLE_COMUN');
INSERT INTO tb_role(authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role(authority) VALUES ('ROLE_ADMIN');


INSERT INTO tb_usuario_role(usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuario_role(usuario_id, role_id) VALUES (2, 2);
INSERT INTO tb_usuario_role(usuario_id, role_id) VALUES (3, 3);
INSERT INTO tb_usuario_role(usuario_id, role_id) VALUES (4, 1);





