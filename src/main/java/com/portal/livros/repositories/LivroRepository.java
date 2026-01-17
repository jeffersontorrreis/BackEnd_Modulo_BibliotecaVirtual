package com.portal.livros.repositories;

import com.portal.livros.dto.LivroDetailsDTO;
import com.portal.livros.entities.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(nativeQuery = true, value = "SELECT obj.* FROM tb_livro obj " +
                                       "WHERE UPPER(obj.titulo) LIKE UPPER(CONCAT('%', :titulo, '%')) " +
                                       "AND UPPER(obj.autor) LIKE UPPER(CONCAT('%', :autor, '%'))" )
    Page<Livro> searchByTituloandAutor(String titulo, String autor, Pageable pageable);

}
