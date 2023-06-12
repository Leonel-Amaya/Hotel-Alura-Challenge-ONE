package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Huesped;
import modelo.Reserva;

public class ReservaDao {
	
	EntityManager em;

	public ReservaDao(EntityManager em) {
		this.em = em;
	}
	
	public ReservaDao() {}

	public void guardar(Reserva reserva) {
		this.em.persist(reserva);
	}
	
	//Método que trae los datos de la DB y la lista
	public List<Reserva> listarDatos() {
		Query query = em.createQuery("SELECT r FROM Reserva r");
		List<Reserva> reservas = query.getResultList();
		return reservas;
	}
	
	public void eliminarReserva(Reserva reserva) {
		reserva = this.em.merge(reserva);
		this.em.remove(reserva);
	}
	
	public void actualizarReserva(Long id, int valor, Date fechaIngreso, Date fechaSalida) {
		try {
			Reserva reserva = em.find(Reserva.class, id);
			
			if(reserva == null) {
				System.out.println("No se encontró el objeto con ID: " + id);
				return;
			}
			
			reserva.setValor(valor);
			reserva.setFecha_ingreso(fechaIngreso);
			reserva.setFecha_salida(fechaSalida);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
