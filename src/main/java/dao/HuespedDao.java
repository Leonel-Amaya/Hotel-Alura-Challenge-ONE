package dao;

import java.util.Date;
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
	
	public void eliminarMedianteReserva(Long reservaId) {
		String jpql = "DELETE FROM Huesped H WHERE H.reserva.id=:reservaId";
		this.em.createQuery(jpql)
				.setParameter("reservaId", reservaId)
				.executeUpdate();
	}
	
	public void actualizarHuesped(Long id, String nombre, String apellido, Date fechaNacimiento, String telefono) {
		try {
			Huesped huesped = this.em.find(Huesped.class, id);
			
			if(huesped == null) {
				System.out.println("No se encontró el objeto con ID: " + id);
				return;
			}
			
			huesped.setNombre(nombre);
			huesped.setApellido(apellido);
			huesped.setBirthday(fechaNacimiento);
			huesped.setTelefono(telefono);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
