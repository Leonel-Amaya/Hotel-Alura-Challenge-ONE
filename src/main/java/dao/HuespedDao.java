package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	public List<Huesped> buscandoHuesped(String consulta) {
		String jpql = "SELECT h FROM Huesped h JOIN h.reserva r WHERE ";
		
		List<Huesped> resultados = null;
		
		if(consulta.matches("\\d+")) {
			// Si el término de búsqueda es un número, buscar por ID de reserva
			jpql += "r.id = :reservaId";
		} else {
			// Si el término de búsqueda es una cadena, buscar por apellido de huesped
		    jpql += "h.apellido LIKE :apellido";
		}
		
		try {
			TypedQuery<Huesped> query = this.em.createQuery(jpql, Huesped.class);
			
			if (consulta.matches("\\d+")) {
		        // Si se busca por ID de reserva, asignar el valor a :reservaId
		        query.setParameter("reservaId", Long.parseLong(consulta));
		    } else {
		        // Si se busca por apellido de huesped, asignar el valor a :apellido
		        query.setParameter("apellido", "%" + consulta + "%");
		    }
			
			resultados = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultados;
	}
}
