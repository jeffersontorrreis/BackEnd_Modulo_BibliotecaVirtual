package com.portal.livros.services;


import com.portal.livros.dto.RoleDTO;
import com.portal.livros.dto.UsuarioDTO;
import com.portal.livros.entities.Role;
import com.portal.livros.entities.Usuario;
import com.portal.livros.projections.UsuarioDetailsProjection;
import com.portal.livros.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    1. Momento que é chamado:
        Quando o usuário faz LOGIN enviando username e password para o endpoint /oauth2/token
    2. Quem chama:
        O Spring Security chama automaticamente este metodo através do CustomPasswordAuthenticationProvider
    3. Onde está sendo chamado:
        Arquivo: CustomPasswordAuthenticationProvider.java (linha ~53)

    O loadUserByUsername é exclusivo para o login, enquanto o authenticated() é usado em todas as outras requisições
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UsuarioDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if(result.size() == 0){
            throw  new UsernameNotFoundException("User not found");
        }
        Usuario user = new Usuario();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        for(UsuarioDetailsProjection projection : result){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    @Transactional
    public UsuarioDTO insert (UsuarioDTO dto){
        Usuario entity = new Usuario();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setFone(dto.getFone());
        entity.setCpf(dto.getCpf());
        entity.setEndereco(dto.getEndereco());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.getRoles().clear();

        for(RoleDTO rolDto : dto.getRoles()){
            Role rol = new Role();
            rol.setId(rolDto.getId());
            entity.getRoles().add(rol);
        }
        entity = repository.save(entity);
        return  new UsuarioDTO(entity);
    }

    @Transactional
    public UsuarioDTO update (UsuarioDTO dto){
            Usuario entity = authenticated();
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setFone(dto.getFone());
            entity.setCpf(dto.getCpf());
            entity.setEndereco(dto.getEndereco());
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));

            /*
            entity.getRoles().clear();

            for(RoleDTO rolDto : dto.getRoles()){
            Role rol = new Role();
            rol.setId(rolDto.getId());
            entity.getRoles().add(rol);   OBS: SOMENTE ADMIN ALTERA PERFIL DE USER
            }
        * */

            entity = repository.save(entity);
            return  new UsuarioDTO(entity);

    }


    //Usado com frequencia qualquer oitro lugar para associarmos com o usuario.
    protected Usuario authenticated(){
        try{
            // Pega o contexto de segurança (informações da autenticação atual)
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Extrai o TOKEN JWT do contexto
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();

            // Pega o EMAIL que está DENTRO do token (claim "username")
            String username = jwtPrincipal.getClaim("username");

            // Busca o usuário completo no banco usando o email. Veja a consulta personalizada no "UsuarioRepository"
            return repository.findByEmail(username).get();
        }
        catch (Exception e){
            throw  new UsernameNotFoundException("User not found");
        }

    }

    @Transactional(readOnly = true)
    public UsuarioDTO getMe(){
        Usuario user = authenticated();
        return new UsuarioDTO(user);
    }
}
//Usuario user = usuarioService.authenticated();