package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Huesped;

public class HuespedDao {
	EntityManager em;

	public HuespedDao(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Huesped huesped) {
		this.em.persist(huesped);
	}
	
	public List<Huesped> listarHuespedes() {
		Query query = em.createQuery("SELECT h FROM Huesped h");
		List<Huesped> huesped = query.getResultList();
		return huesped;
	}
}
