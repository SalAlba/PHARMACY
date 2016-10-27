package pl.kielce.tu.pharmacy.web.managed;

import java.util.List;

import pl.kielce.tu.pharmacy.core.model.Product;
import pl.kielce.tu.pharmacy.web.back.ProductBean;

public interface ProductActionBean {

	ProductBean getProductBean();

	void setProductBean(ProductBean productBean);

	void addProduct();

	void updateProduct();

	void getProduct(int id);

	void removeProduct(int id);

	void clean();
	
	public List<Product> getProductsByType(String type);

}