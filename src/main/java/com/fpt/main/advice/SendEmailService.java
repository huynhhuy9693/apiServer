package com.fpt.main.advice;






import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.fpt.main.dto.Purchase;



@Component
public class SendEmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	//send mail with HTML basic
		public void sendEmailForInvoice(Purchase purchase, String orderTrackingNumber) throws MalformedURLException, MessagingException, UnsupportedEncodingException {
							
			URL activeUrl = new URL("http://localhost:8080/api/report/pdf-report/" + orderTrackingNumber);
			URL qrCodeUrl = new URL("http://localhost:8080/api/report/qrcode/" + orderTrackingNumber);
					
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			
			helper.setFrom("Admin@admin.com", "Admin");
			helper.setTo(purchase.getCustomer().getEmail());
			
			
			String customerStr = purchase.getCustomer().getFullName();
			String totalAmount = String.valueOf(purchase.getOrder().getTotalPrice());			
			String subject =  "Thank you for using the service from Food-Order";
			String content = "<p>Hello," + customerStr + "</p>"
					+ "<p>We are glad you used our service. We would like to ask you to send detailed order information:<p>"
					+ "<p>Tracking number : "+ orderTrackingNumber +"</p>"
					+ "<p>Total amount on bill: " +  totalAmount + "</p>"  
					+  "Link download invoice: " + activeUrl +"</p>"
					+  "QR code : "+ qrCodeUrl+"</p>"
					+ "<p></p>";
			
			
			helper.setSubject(subject);
			helper.setText(content, true);
			
			javaMailSender.send(message);
		}
	
}
