package pl.kielce.tu.pharmacy.web.managed;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pl.kielce.tu.pharmacy.core.manager.PrescriptionManager;
import pl.kielce.tu.pharmacy.core.model.Prescription;
import pl.kielce.tu.pharmacy.web.messages.Messages;
import pl.kielce.tu.pharmacy.web.back.PrescriptionBean;

@Named("defaultPrescriptionActionBean")
@RequestScoped
public class DefaultPrescriptionActionBean implements PrescriptionActionBean
{	
	@Inject
    private PrescriptionBean prescriptionBean;
	
	@EJB
	private PrescriptionManager prescriptionManager;
	
	public DefaultPrescriptionActionBean() 
	{
    }

	@Override
	public PrescriptionBean getvBean() {
		return prescriptionBean;
	}

	@Override
	public void setPrescriptionBean(PrescriptionBean prescriptionBean) {
		this.prescriptionBean = prescriptionBean;
	}
	
	@Override
	public void addPrescription() 
	{
		prescriptionManager.createPrescription(prescriptionBean.getPrescription());
	}
	
	@Override
	public void completePrescription(String prescriptionCode)
	{	
		if(prescriptionCode.isEmpty())
		{
			Messages.infoMessage("Prescription: ", "Enter prescription code.");
		}
		else
		{
			Prescription p = prescriptionManager.existPrescription(Integer.parseInt(prescriptionCode));
			
			if(p != null)
			{	
				DateFormat df = new SimpleDateFormat("dd/MM/yy");
				Date dateobj = new Date();
					
				String executionDate = df.format(dateobj);

				p.setExecutionDate(executionDate);
				p.setState(1);
				
				prescriptionManager.updatePrescription(p);
				Messages.infoMessage("Prescription: ", "Updated execution date and state of prescription.");
			}
			else
			{
				Messages.infoMessage("Prescription: ", "This prescription code does not exist.");
			}
		}
	}
}
