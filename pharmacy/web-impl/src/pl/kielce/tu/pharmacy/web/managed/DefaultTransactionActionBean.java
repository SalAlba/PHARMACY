package pl.kielce.tu.pharmacy.web.managed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pl.kielce.tu.pharmacy.core.manager.ProductManager;
import pl.kielce.tu.pharmacy.core.manager.TransactionManager;
import pl.kielce.tu.pharmacy.core.model.Product;
import pl.kielce.tu.pharmacy.web.back.TransactionBean;

@Named("defaultTransactionActionBean")
@ApplicationScoped
public class DefaultTransactionActionBean implements TransactionActionBean
{	
	@Inject
    private TransactionBean transactionBean;
	
	private List<Product> productCart;
	
	@EJB
	private TransactionManager transactionManager;
	
	@EJB
	private ProductManager productManager;
	
	private double priceOfCart;
	
	private int sizeOfCart;
	
	public DefaultTransactionActionBean() 
	{
    }
	
	@Override
	public List<Product> getProductCart() {
		return productCart;
	}

	@Override
	public void setProductCart(List<Product> productCart) {
		this.productCart = productCart;
	}

	@Override
	public TransactionBean getTransactionBean() {
		return transactionBean;
	}

	@Override
	public void setTransactionBean(TransactionBean transactionBean) {
		this.transactionBean = transactionBean;
	}
	
	@Override
	public void addTransaction() 
	{
		transactionManager.createTransaction(transactionBean.getTransaction());
	}
	
	@Override
	@PostConstruct
	public void init() 
	{
		this.productCart = new ArrayList<Product>();
	}
	
	@Override
	public void addProductToCart(Product product)
	{	
		this.productCart.add(product);
		
		this.priceOfCart += product.getPrice();
		this.sizeOfCart++;
	}
	
	@Override
	public void removeProductInCart(Product product)
	{
		this.productCart.remove(product);
		
		this.priceOfCart -= product.getPrice();
		this.sizeOfCart--;
	}
	
	@Override
	public void clearCart()
	{
		this.productCart.clear();
		
		this.sizeOfCart = 0;
		this.priceOfCart = 0;
	}
	
	@Override
	public void setPriceOfCart()
	{
		for(Product p : productCart)
		{
			this.priceOfCart += p.getPrice();
		}
	}
	
	@Override
	public double getPriceOfCart()
	{
		return this.priceOfCart;
	}
	
	@Override
	public void setSizeOfCart()
	{
		this.sizeOfCart = this.productCart.size();
	}
	
	@Override
	public int getSizeOfCart()
	{
		return this.sizeOfCart;
	}
}
