package model.service;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double pricePerHour;
	private Double pricePerDay;
	private TaxService taxservice;
	
	
	public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxservice) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxservice = taxservice;
	}


	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public TaxService getTaxservice() {
		return taxservice;
	}

	public void setTaxservice(TaxService taxservice) {
		this.taxservice = taxservice;
	}
	
	public void processinvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours = (double)(t2-t1)/1000/60/60;
		
		double basicPaymen;
		if(hours <= 12.0) {
			basicPaymen = Math.ceil(hours) * pricePerHour;
		}
		
		else {
			basicPaymen = Math.ceil(hours/24) * pricePerDay;
		}
		double tax = taxservice.tax(basicPaymen);
			
		carRental.setInvoice(new Invoice(basicPaymen, tax));
	}
	
	
	
	
}
