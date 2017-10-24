package com.babytrak24.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Baby {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
    private String profileImageS3Path;

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

 

    /**
     * @return the First Name
     */
    public String getFirstname() {
        return firstname;
    }
    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the Last Name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfileImageS3Path() {
		return profileImageS3Path;
	}
	public void setProfileImageS3Path(String profileImageS3Path) {
		this.profileImageS3Path = profileImageS3Path;
	}
	
	
}
