package com.babytrak24.model;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String profileImageS3Path;
    private Date uploadTime;
    private Date updatedTime;
    @Column(unique = true)
    private String fileName;
    private String fileDescription;


    public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDescription() {
		return fileDescription;
	}
	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	@OneToMany(mappedBy="baby")
    private Set<Feed> feeds = new HashSet<Feed>();

    @OneToMany(mappedBy="baby")
    private Set<PeePoop> peePoops = new HashSet<PeePoop>();
    /**
     * @return the feeds
     */
    public Set<Feed> getFeeds() {
        return feeds;
    }
    /**
     * @param feeds the feeds to set
     */
    public void setFeeds(Set<Feed> feeds) {
        this.feeds = feeds;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
		this.email = email;
	}
    
    public String getEmail() {
		return email;
	}
	public String getProfileImageS3Path() {
		return profileImageS3Path;
	}
	public void setProfileImageS3Path(String profileImageS3Path) {
		this.profileImageS3Path = profileImageS3Path;
	}
	
	
}
