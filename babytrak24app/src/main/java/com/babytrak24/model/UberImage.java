/**
 * 
 */
package com.babytrak24.model;

/**
 * @author rajisunder
 *
 */
public class UberImage {
	
	private Transaction transaction;
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	private String imagePath;

}
