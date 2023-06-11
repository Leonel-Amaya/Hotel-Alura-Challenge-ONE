package controller;

import javax.persistence.EntityManager;

import dao.HuespedDao;
import dao.ReservaDao;
import modelo.Huesped;
import modelo.Reserva;

public class HuespedController {
	
	EntityManager em;
	
	
	
	public HuespedController(EntityManager em) {
		this.em = em;
	}



	public void eliminarRegistro(Long id) {
		// Obteniendo los datos
		Huesped huesped = this.em.find(Huesped.class, id);
		Long idReserva = huesped.getReserva().getId();
		Reserva reserva = this.em.find(Reserva.class, idReserva);
		HuespedDao huespedDao = new HuespedDao(em);
		ReservaDao reservaDao = new ReservaDao(em);
		
		//Probando
//		System.out.println(huesped.getNombre());
//		System.out.println(reserva.getFormaPago());
//		
		// Eliminando huesped
		huespedDao.eliminarHuesped(huesped);
		reservaDao.eliminarReserva(reserva);
		
	}
	
}
