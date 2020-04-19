package com.bolsadeideas.springboot.di.app.models.domain;

public class ItemInvoice {

	private Product product;

	private Integer quantity;

	public Product getProduct() {
		return product;
	}

	public ItemInvoice(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
