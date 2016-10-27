package com.pharmacy.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("AccountNumberConverter")
public class FacesAccountNumberConverter implements Converter {

	private AccountNumberConverter accountNumberConverter = new AccountNumberConverter();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			return accountNumberConverter.convertToString(value);
		} catch (NumberFormatException ex) {
			 FacesMessage msg = new FacesMessage("Error converting URL","Niepoprawny format!");
	         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//	         throw new ConverterException(msg);	         
//			((UIInput) component).setValid(false);
//			context.addMessage(component.getClientId(),
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny format!", null));

			return "";
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return accountNumberConverter.convertToFriendlyString((String) value);
	}

}
