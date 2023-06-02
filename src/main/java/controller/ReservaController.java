package controller;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.ReservaDao;
import modelo.Reserva;

public class ReservaController {
	private static int valorHospedaje;
	private static String formaPago;
	private static Date fecha_ingreso;
	private static Date fecha_salida;
	
	public ReservaController(int valorHospedaje, String formaPago, Date fecha_ingreso, Date fecha_salida) {
		ReservaController.valorHospedaje = valorHospedaje;
		ReservaController.formaPago = formaPago;
		ReservaController.fecha_ingreso = fecha_ingreso;
		ReservaController.fecha_salida = fecha_salida;
	}

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebahotel");
		EntityManager em = factory.createEntityManager();
		
		Reserva reserva = new Reserva(valorHospedaje, formaPago, fecha_ingreso, fecha_salida);
		ReservaDao reservaDao = new ReservaDao(em);
		
		em.getTransaction().begin();
		
		reservaDao.guardar(reserva);
		
		em.getTransaction().commit();
		em.close();
	}
	
}
