package com.portal.livros.repositories;

import com.portal.livros.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByClientId(Long clientId); //Esse metodo esta sendo usado para retornar findAll no service.

    Optional<Emprestimo> findByIdAndClientId(Long id, Long clientId); /*Aqui vamos usar a entidade "Emprestimo" direto ja que
                                                                        queremos trazer apenas um objeto por vez. Nesse caso um id por vez.*/

    /*De qualquer forma ambos os metodos trazem os dados da coluna ClientId.*/

    @Query("SELECT e FROM Emprestimo e " +
            "WHERE e.client.cpf LIKE CONCAT(:cpf, '%') ")
    List<Emprestimo> findByClientCpf(String cpf); /*Veja que nessa query estamos chegando na coluna "cpf" da tebela
                                                    "usuarios" atraves da coluna "ClientId" pela tabela "Emprestimo"*/

    /*Obs: Depois veja no GUIA na pasta DEVSUPERIOR o proque não usamos projection aqui.*/
}
