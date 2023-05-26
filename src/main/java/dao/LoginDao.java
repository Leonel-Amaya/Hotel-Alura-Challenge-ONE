package dao;

import javax.persistence.EntityManager;

import modelo.LoginModelo;


public class LoginDao {
	EntityManager em;

	public LoginDao(EntityManager em) {
		this.em = em;
	}
	
	public LoginModelo consultarLogin(String user, String password) {
		String jpql = "SELECT L FROM LoginModelo AS L WHERE L.user=:user AND L.password=:password";
		return em.createQuery(jpql, LoginModelo.class).setParameter("user",user).setParameter("password", password).getSingleResult();
	}
}
