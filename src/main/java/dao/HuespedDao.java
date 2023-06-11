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
	
	// Método que trae los datos de la DB y los enlista
	public List<Huesped> listarHuespedes() {
		Query query = em.createQuery("SELECT h FROM Huesped h");
		List<Huesped> huesped = query.getResultList();
		return huesped;
	}

	public void eliminarHuesped(Huesped huesped) {
		huesped = this.em.merge(huesped);
		this.em.remove(huesped);
	}
}
