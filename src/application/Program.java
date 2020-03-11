package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		System.out.println("Enter rental data");
		System.out.print("Car Model: ");
		String name = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		CarRental car = new CarRental(start, finish, new Vehicle(name));
		
		System.out.print("Enter price per hour: ");
		double Pricehour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double Priceday = sc.nextDouble();
		
		RentalService rentalservice = new RentalService(Pricehour, Priceday, new BrazilTaxService());
		rentalservice.processinvoice(car);
		
		System.out.println("INVOICE:");
		System.out.println("Basic payment: " +String.format("%.2f", car.getInvoice().getBasicPayment()));
		System.out.println("Tax : " +String.format("%.2f",car.getInvoice().getTax()));
		System.out.println("total payment: " +String.format("%.2f",car.getInvoice().getTotalPaymen()));
		sc.close();
	}

}
