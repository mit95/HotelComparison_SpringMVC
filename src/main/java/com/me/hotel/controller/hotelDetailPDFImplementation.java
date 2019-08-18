package com.me.hotel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.me.hotel.dao.HotelTypeDAO;
import com.me.hotel.exception.HotelException;
import com.me.hotel.pojo.Hotel;

public class hotelDetailPDFImplementation extends AbstractPdfView{

	private HotelTypeDAO hotelTypeDao;
	private String selectedHotelType;
	public hotelDetailPDFImplementation(HotelTypeDAO hotelTypeDao,String selectedHotelType) {
		this.selectedHotelType = selectedHotelType;
		this.hotelTypeDao = hotelTypeDao;
	}
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Font font = new Font(Font.TIMES_ROMAN, 24, Font.BOLD);
		Paragraph heading = new Paragraph("List of Hotels", font );
		Font hotelTitleFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
		document.add(heading);
		String ht = selectedHotelType;
		try {
			List<Hotel> hotelList = hotelTypeDao.hotelList(ht);
			for(Hotel h: hotelList) {
				 document.add(new Phrase("Name: "+h.getHotelName(),hotelTitleFont)) ;
				 document.add( Chunk.NEWLINE );
				 document.add(new Phrase("	Price: "+h.getHotelPrice()));
				 document.add(new Phrase("	Address: "+h.getHotelAddress()));
				 document.add( Chunk.NEWLINE );
				 document.add( Chunk.NEWLINE );
			}
			
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
