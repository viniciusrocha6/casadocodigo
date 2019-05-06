package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email).getResultList();

		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}

		return usuarios.get(0);
	}

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}

	public List<Usuario> listar() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = criteriaBuilder.createQuery(Usuario.class);
		query.from(Usuario.class);
		TypedQuery<Usuario> typedQuery = manager.createQuery(query);
		return typedQuery.getResultList();

	}

	public Boolean checkEmail(Usuario usuario) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);
		Path<String> emailPath = root.<String>get("email");
		Predicate predicate = criteriaBuilder.equal(emailPath, usuario.getEmail());
		query.where(predicate);

		TypedQuery<Usuario> typedQuery = manager.createQuery(query);
		if (typedQuery.getResultList().isEmpty()) {
			this.gravar(usuario);
			return false;
		} else {
			return true;
		}

	}

	public Usuario find(String email) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);
		Path<String> emailPath = root.<String>get("email");
		Predicate predicate = criteriaBuilder.equal(emailPath, email);
		query.where(predicate);

		TypedQuery<Usuario> typedQuery = manager.createQuery(query);
		return typedQuery.getSingleResult();
	}

	public void atualiza(Usuario usuario) {

		System.out.println(usuario.toString());

	}
}