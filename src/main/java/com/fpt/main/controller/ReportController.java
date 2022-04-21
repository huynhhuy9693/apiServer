package com.fpt.main.controller;


import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fpt.main.helpers.ZXingHelper;
import com.fpt.main.services.ReportService;




@RestController
@RequestMapping("/api/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping(value = "pdf-report/{orderTrackingNumber}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getPDFReport(@PathVariable ("orderTrackingNumber") String orderTrackingNumber) {
		try {
			return new ResponseEntity<byte[]>(reportService.generatePDFReport(orderTrackingNumber), HttpStatus.OK);
//			return new ResponseEntity<byte[]>(HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//QR	
	@GetMapping(value = "qrcode/{orderTrackingNumber}", produces = MediaType.APPLICATION_PDF_VALUE)
	public void qrcode(@PathVariable("orderTrackingNumber") String orderTrackingNumber , HttpServletResponse response) throws Exception
	{
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getQRCodeImage(orderTrackingNumber, 200, 200));
		outputStream.flush();
		outputStream.close();
		
	}
}
