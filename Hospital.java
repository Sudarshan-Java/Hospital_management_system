package HospitalManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DatabaseService.getConnection()) {
            if (conn == null) {
                System.out.println("Failed to connect to the database.");
                return;
            }

            Patient patient = new Patient(conn);
            Doctor doctor = new Doctor(conn);
            BookAppointment appointment = new BookAppointment(conn, patient, doctor);

            while (true) {
                System.out.println("=== Hospital Management System ===");
                System.out.println("1. Add Patient");
                System.out.println("2. Add Doctor");
                System.out.println("3. View Patients");
                System.out.println("4. View Doctors");
                System.out.println("5. Book Appointment");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                
                int ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        doctor.addDoctor();
                        break;
                    case 3:
                        patient.viewPatients();
                        break;
                    case 4:
                        doctor.viewDoctors();
                        break;
                    case 5:
                        appointment.bookAppointment();
                        break;
                    case 6:
                        System.out.println("Exiting system.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Please enter a valid choice.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
