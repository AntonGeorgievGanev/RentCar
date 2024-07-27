package bg.rentacar.service;

public interface EmailService {
    void sendEmail(String to, String subject, String message);
}
