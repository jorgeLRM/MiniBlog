package com.mitocode.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mitocode.dao.IUsuarioDAO;
import com.mitocode.model.Usuario;

@Stateless
public class UsuarioDAOImpl implements IUsuarioDAO, Serializable {

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;
	
	@Override
	public Integer registrar(Usuario t) throws Exception {
		em.persist(t);
		return t.getPersona().getIdPersona();
	}

	@Override
	public Integer modificar(Usuario t) throws Exception {
		em.merge(t);
		return t.getPersona().getIdPersona();
	}

	@Override
	public Integer eliminar(Usuario t) throws Exception {
		em.remove(em.merge(t));
		return 1;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			Query query = em.createQuery("SELECT p FROM Usuario p");
			lista = (List<Usuario>) query.getResultList();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	@Override
	public Usuario listarPorId(Usuario t) throws Exception {
		Usuario us = new Usuario();
		List<Usuario> lista = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM Usuario u WHERE u.id = ?1");
			query.setParameter(1, t.getPersona().getIdPersona());
			
			lista = (List<Usuario>) query.getResultList();
			
			if (lista != null && !lista.isEmpty()) {
				us = lista.get(0);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return us;
	}

	@Override
	public String traerPassHashed(String us) {
		Usuario usuario = new Usuario();
		try {
			Query query = em.createQuery("FROM Usuario u WHERE u.usuario = ?1");
			query.setParameter(1, us);
			
			List<Usuario> lista = query.getResultList();
			if (!lista.isEmpty()) {
				usuario = lista.get(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return usuario != null && usuario.getId() != null ? usuario.getContrasena() : "";
	}

	@Override
	public Usuario leerPorNombreUsuario(String us) {
		Usuario usuario = new Usuario();
		List<Usuario> lista = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM Usuario u WHERE u.usuario = ?1");
			query.setParameter(1, us);
			
			lista = (List<Usuario>) query.getResultList();
			
			if (lista != null && !lista.isEmpty()) {
				usuario = lista.get(0);
			}
		}catch(Exception e) {
			throw e;
		}
		return usuario;
	}

}



















