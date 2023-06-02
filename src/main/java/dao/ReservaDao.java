package dao;

import javax.persistence.EntityManager;

import modelo.Reserva;

public class ReservaDao {
	
	EntityManager em;

	public ReservaDao(EntityManager em) {
		this.em = em;
	}
	
	
	
	public ReservaDao() {
	}



	public void guardar(Reserva reserva) {
		this.em.persist(reserva);
	}

}
