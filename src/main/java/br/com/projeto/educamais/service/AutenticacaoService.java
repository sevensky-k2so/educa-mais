package br.com.projeto.educamais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.educamais.domain.Usuario;
import br.com.projeto.educamais.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService  {

	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		if(usuario != null) {
			return usuario;
		}
		
		throw new UsernameNotFoundException("Usuário inválido!");
	}
}
