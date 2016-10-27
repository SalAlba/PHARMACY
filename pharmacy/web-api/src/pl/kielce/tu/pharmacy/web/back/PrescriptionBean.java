package pl.kielce.tu.pharmacy.web.back;

import pl.kielce.tu.pharmacy.core.model.Prescription;

public interface PrescriptionBean {

	Prescription getPrescription();

	void setPrescription(Prescription prescription);

}