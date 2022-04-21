package com.fpt.main.services;




import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fpt.main.dto.ReportDto;

import com.fpt.main.entity.OrderItem;
import com.fpt.main.entity.Restaurant;
import com.fpt.main.reponsitory.CustomerRespository;
import com.fpt.main.reponsitory.DishesRepository;
import com.fpt.main.reponsitory.OrderItemRepository;
import com.fpt.main.reponsitory.RestaurantRepository;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;




@Service
public class ReportService {
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	CustomerRespository customerRespository;
	
	@Autowired
	DishesRepository dishesRepository;
				
	@Autowired
	RestaurantRepository restaurantRepository;
	
	public byte[] generatePDFReport(String orderTrackingNumber) throws FileNotFoundException, JRException {
		
		JasperReport jasperReport = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/Invoice.jrxml").getAbsolutePath());		
		
		
		//handle Datasource
		ArrayList<OrderItem> itemList = (ArrayList<OrderItem>) orderItemRepository.findAllByOrderTrackingNumber(orderTrackingNumber);
		List<ReportDto> dataSource = new ArrayList<>();
		
		for (OrderItem orderItem : itemList) {
			System.out.println(orderItem);
			ReportDto item = new ReportDto();
			item.setId(orderItem.getId());
			item.setQuantity(orderItem.getQuantity());
			item.setUnit_price(Float.parseFloat(String.valueOf(orderItem.getUnitPrice())));
			
			String dishName = dishesRepository.getNameDishById(orderItem.getDishesId());
			if (dishName != "" && dishName != null) {
				item.setName(dishName);
			}
			
			dataSource.add(item);
		}		
		JRBeanCollectionDataSource dataSource_ = new JRBeanCollectionDataSource(dataSource);
		
		//handle parameters				
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("customer_name", itemList.get(0).getOrder().getCustomer().getEmail()); 
		parameters.put("phone_number", itemList.get(0).getOrder().getCustomer().getPhoneNumber()); 
		parameters.put("customer_email", itemList.get(0).getOrder().getCustomer().getEmail()); 
		parameters.put("shipping_address", itemList.get(0).getOrder().getShippingAddress().getFullAddress());
		
		Restaurant restaurant = restaurantRepository.findByDishesListId(itemList.get(0).getDishesId());
		
		parameters.put("restaurant_name", restaurant.getFullName()); 
		parameters.put("restaurant_address", restaurant.getAddress().getFullAddress()); 

		parameters.put("tracking_number", orderTrackingNumber); 
		parameters.put("total_price", String.valueOf(itemList.get(0).getOrder().getTotalPrice()));
		parameters.put("shipping_money", String.valueOf(itemList.get(0).getOrder().getShippingMoney()));
		parameters.put("payment", itemList.get(0).getOrder().getStatus());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource_);

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
}
