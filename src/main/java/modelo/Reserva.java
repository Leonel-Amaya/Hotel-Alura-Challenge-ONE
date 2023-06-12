package modelo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservas")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int valor;
	private String formaPago;
	private Date fecha_ingreso;
	private Date fecha_salida;
	
	public Reserva() {}

	public Reserva(int valor, String formaPago, Date fecha_ingreso, Date fecha_salida) {
		this.valor = valor;
		this.formaPago = formaPago;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_salida = fecha_salida;
	}

	public Reserva(int valor, Date dateCheckIn, Date dateCheckOut) {
		this.valor = valor;
		this.fecha_ingreso = dateCheckIn;
		this.fecha_salida = dateCheckOut;
	}

	public Long getId() {
		return id;
	}

	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String getFormaPago() {
		return formaPago;
	}
	
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	
	public Date getFecha_salida() {
		return fecha_salida;
	}
	
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	
	public long diasHospedaje(Date fecha_ingreso, Date fecha_salida) {
		return 20 * (TimeUnit.MILLISECONDS.toDays(fecha_salida.getTime() - fecha_ingreso.getTime()));
	}
	

}
