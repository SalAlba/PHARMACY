package pl.kielce.tu.pharmacy.web.managed;

import pl.kielce.tu.pharmacy.web.back.PrescriptionBean;

public interface PrescriptionActionBean {

	PrescriptionBean getvBean();

	void setPrescriptionBean(PrescriptionBean prescriptionBean);

	void addPrescription();

	void completePrescription(String prescriptionCode);

}