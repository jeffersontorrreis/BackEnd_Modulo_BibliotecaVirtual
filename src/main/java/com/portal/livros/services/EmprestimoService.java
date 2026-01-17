package com.portal.livros.services;

import com.portal.livros.dto.HistoricoEmprestimoDTO;
import com.portal.livros.dto.HistoricoEmprestimoDetailsDTO;
import com.portal.livros.dto.SolicitarEmprestimoDTO;
import com.portal.livros.dto.SolicitarEmprestimoItemDTO;
import com.portal.livros.entities.Emprestimo;
import com.portal.livros.entities.EmprestimoItem;
import com.portal.livros.entities.Usuario;
import com.portal.livros.repositories.EmprestimoItemRepository;
import com.portal.livros.repositories.EmprestimoRepository;
import com.portal.livros.repositories.LivroRepository;
import com.portal.livros.repositories.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoItemRepository emprestimoItemRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    //Visão de busca de historico por bibliotecario e admin
    @Transactional(readOnly = true)
    public List<HistoricoEmprestimoDetailsDTO> findAll(String cpf) {
        Usuario user = usuarioService.authenticated(); // Pega o usuario logado pelo token
        List<Emprestimo> result;

        // Se for ADMIN, busca todos os emprestimos (emprestados ou devolvidos), além de buscar por cpf
        if (user.hasRole("ROLE_ADMIN")) {
            result = emprestimoRepository.findByClientCpf(cpf);
        }
        // Se for ADMIN, busca todos os (emprestimos emprestados ou devolvidos), além de buscar por cpf
        else if(user.hasRole("ROLE_OPERATOR")){
            result = emprestimoRepository.findByClientCpf(cpf);
        }

        // Se não for ADMIN e nem OPERATOR, logo o perfil é COMUM, e nesse caso, cada usuario logado só acessa seus proprios emprestimos
        else {
            result = emprestimoRepository.findByClientId(user.getId());
                    //Se não for nem admin e nem operator faz uma consulta filtrando por clientId e mostra somente os emrpestimos do user logado.
        }
        return result.stream()
                .map(x -> new HistoricoEmprestimoDetailsDTO(x))
                .toList();
    }

    //Visão de Busca de historico por usuario comum
    @Transactional(readOnly = true)
    public List<HistoricoEmprestimoDTO> findAll() {
        Usuario user = usuarioService.authenticated(); // Pega o usuario logado pelo token
        List<Emprestimo> result = emprestimoRepository.findByClientId(user.getId());
            //Se não for nem admin e nem operator faz uma consulta filtrando por clientId e mostra somente os emrpestimos do user logado.

        return result.stream()
                .map(x -> new HistoricoEmprestimoDTO(x))
                .toList();
    }


    //Visão de Busca de historico detalhado por id por parte do bibliotecario e admin
    @Transactional(readOnly = true)
    public HistoricoEmprestimoDetailsDTO findByIdDetails(Long id) {
        Usuario user = usuarioService.authenticated();
        Emprestimo emp = new Emprestimo();
        if (user.hasRole("ROLE_ADMIN")) {
            emp = emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emprestimo não encontrado!"));
            /*Não esqueça que o findById ja tras o Optional por de baixo dos panos, mesmo não
              precisando infromar o optional do findBiId no "emprestimoRepository". E como é Optional
              pode retornar os dados ou vazio, e se for vazio ja lançamos a exceção com o ".orElseThrow...*/
        }

        else if(user.hasRole("ROLE_OPERATOR")){
            emp = emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emprestimo não encontrado!"));
            /*Não esqueça que o findById ja tras o Optional por de baixo dos panos, mesmo não
              precisando infromar o optional do findBiId no "emprestimoRepository". E como é Optional
              pode retornar os dados ou vazio, e se for vazio ja lançamos a exceção com o ".orElseThrow..."*/
        }
        else {
            emp = emprestimoRepository.findByIdAndClientId(id, user.getId()).orElseThrow(() -> new ResourceNotFoundException("Emprestimo não encontrado!"));
            /*Aqui como se trata de um metodo criado "findByIdAndClientId" no repository e não é um page<> e nem list<>, então
              foi retorna um objeto ou vazio, logo usamos o optional, e seguindo p padrão, se tem optional epode ter dados ou
               vazio. Então podemos lançar logo a exceção com o ".orElseThrow...."*/
        }
        return new HistoricoEmprestimoDetailsDTO(emp);
    }

    @Transactional(readOnly = true)
    public HistoricoEmprestimoDTO findById(Long id) {
        Usuario user = usuarioService.authenticated();
        Emprestimo emp = emprestimoRepository.findByIdAndClientId(id, user.getId()).orElseThrow(() -> new ResourceNotFoundException("Emprestimo não encontrado!"));

        return new HistoricoEmprestimoDTO(emp);
    }

    /*IMPORTANTE: Podemos usar dois metodos iguais, sejam dois "findAll" ou dois "findById" , mas desde que os parametros passados nos metodos sejam todos
      diferentes. Do contrario teremos metodos ambiguos.*/

    @Transactional
    public SolicitarEmprestimoDTO insert(SolicitarEmprestimoDTO dto){
        Emprestimo entity = new Emprestimo();
        entity.setDataEmprestimo(dto.getDataEmprestimo());
        entity.setDataDevolucaoPrevista(dto.getDataDevolucaoPrevista());
        entity.setDataDevolucaoReal(dto.getDataDevolucaoReal());
        entity.setStatus(dto.getStatus());

        // Associar o usuário logado ao empréstimo
        entity.setClient(usuarioService.authenticated());

        entity.getItems().clear();
        //            Alvo              Iterador   Fonte de dados
        for (SolicitarEmprestimoItemDTO itemDTO : dto.getItems()){
            EmprestimoItem item = new EmprestimoItem(entity, livroRepository.getReferenceById(itemDTO
                    .getLivroId()), itemDTO.getQuantidadeEmprestimo(), itemDTO.getQuantidadeDevolvida());
                    // Lembre-se que aqui estamos adicionando um novo emprestimo com novos items, e por isso podemos cololar dessa forma.

            entity.getItems().add(item);
        }

        // Salvar o empréstimo (isso também salvará os itens em cascata se configurado)
        entity = emprestimoRepository.save(entity);

        // Retornar o DTO com os dados do empréstimo salvo
        return new SolicitarEmprestimoDTO(entity);
    }

    @Transactional
    public SolicitarEmprestimoDTO update(Long id, SolicitarEmprestimoDTO dto){
        try {
            Emprestimo entity = emprestimoRepository.findById(id).get();
            entity.setDataEmprestimo(dto.getDataEmprestimo());
            entity.setDataDevolucaoPrevista(dto.getDataDevolucaoPrevista());
            entity.setDataDevolucaoReal(dto.getDataDevolucaoReal());
            entity.setStatus(dto.getStatus());

            // Associar o usuário logado ao empréstimo
            //entity.setClient(usuarioService.authenticated());

            entity.getItems().clear();
            //            Alvo              Iterador   Fonte de dados
            for (SolicitarEmprestimoItemDTO itemDTO : dto.getItems()){
                EmprestimoItem item = new EmprestimoItem(entity, livroRepository.getReferenceById(itemDTO
                        .getLivroId()), itemDTO.getQuantidadeEmprestimo(), itemDTO.getQuantidadeDevolvida());
                        // Lembre-se que aqui estamos adicionando um novo emprestimo com novos items, e por isso podemos cololar dessa forma.

                entity.getItems().add(item);
            }

            // Salvar o empréstimo (isso também salvará os itens em cascata se configurado)
            entity = emprestimoRepository.save(entity);

            // Retornar o DTO com os dados do empréstimo salvo
            return new SolicitarEmprestimoDTO(entity);
        }
        catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Emprestimo não encontrado!");
        }
    }

}

