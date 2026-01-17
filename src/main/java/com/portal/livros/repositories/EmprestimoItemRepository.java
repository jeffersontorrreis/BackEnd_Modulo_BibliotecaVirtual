package com.portal.livros.repositories;

import com.portal.livros.entities.EmprestimoItem;
import com.portal.livros.entities.EmprestimoItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoItemRepository extends JpaRepository<EmprestimoItem, EmprestimoItemPK> {
}
