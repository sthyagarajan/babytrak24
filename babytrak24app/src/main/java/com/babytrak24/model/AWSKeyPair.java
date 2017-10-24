/**
 * 
 */
package com.babytrak24.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author rajisunder
 *
 */

@Entity
public class AWSKeyPair {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String accessKey;
	private String accessToken;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String bucketname;

	public String getBucketname() {
		return bucketname;
	}

	public void setBucketname(String bucketname) {
		this.bucketname = bucketname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}