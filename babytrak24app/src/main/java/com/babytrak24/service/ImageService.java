package com.babytrak24.service;

import java.io.File;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.babytrak24.model.AWSKeyPair;

public class ImageService {

	public ImageService(String email) {
		super();
		AWSKeyPair inputawsKeyPair = new AWSKeyPair();
		inputawsKeyPair.setEmail(email);
		inputawsKeyPair = awsKeyPairService.findOne(Example.<AWSKeyPair>of(inputawsKeyPair,
				ExampleMatcher.matching().withMatcher("email", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));

		bucketFolderName = BUCKET_NAME + inputawsKeyPair.getBucketname();
		awsCredentials = new BasicAWSCredentials(inputawsKeyPair.getAccessKey(), inputawsKeyPair.getAccessToken());
		awsS3 = new AmazonS3Client(awsCredentials);

	}
	public ImageService() {
		super();
		
		

		bucketFolderName = BUCKET_NAME + "XXXXXXXXXXXXXXXX";
		awsCredentials = new BasicAWSCredentials(ACCESS_KEY, ACCESS_TOKEN);
		awsS3 = new AmazonS3Client(awsCredentials);

	}

	private AmazonS3Client awsS3;
	private String bucketFolderName;
	private AWSCredentials awsCredentials;
	private AWSKeyPairService awsKeyPairService;
	private final String BUCKET_NAME = "XXXXXXXXXXXXXXXXXX";
	private final String BUCKET_URL = "XXXXXXXXXXXXXXXXXXXXXXX";
	private final String CLOUD_FRONT_DOMAIN = "XXXXXXXXXXXXXXXXXXXXXXXXX";
	
	private final String ACCESS_KEY = "XXXXXXXXXXXXXXXXXXXXXXXX";
	private final String ACCESS_TOKEN = "XXXXXXXXXXXXXXXXXXXXXXXXX";

	/*
	 * Upload a given filename to Users folder ( Users Folder preconfigured with
	 * Access Key in RDS ) Add can be used for both update and create
	 */
	public Boolean add(String name, File fileToUpload) {
		Boolean uploadedtoS3 = Boolean.TRUE;
		try {
			awsS3.putObject(new PutObjectRequest(bucketFolderName, name, fileToUpload));
		} catch (Exception exception) {
			uploadedtoS3 = Boolean.FALSE;
		}
		return uploadedtoS3;
	}

	/*
	 * Delete a given filename from Users folder ( Users Folder preconfigured with
	 * Access Key in RDS )
	 */
	public void delete(String fileName) {
		awsS3.deleteObject(new DeleteObjectRequest(bucketFolderName, fileName));

	}

	/*
	 * Retrieve a given filename from Users folder ( Users Folder preconfigured with
	 * Access Key in RDS )
	 */
	public String read(String fileName) {
		S3Object s3Object = awsS3.getObject(new GetObjectRequest(bucketFolderName, fileName));
		String path = s3Object.getObjectContent().getHttpRequest().getURI().toString();
		if (path != null) {
			path = path.replaceAll(BUCKET_URL, CLOUD_FRONT_DOMAIN);
		}
		return path;
	}
	
	//TODO Used for learning, will be removed after final project
	public void bucketList() {
		List<Bucket> buckets = awsS3.listBuckets();

		for (Bucket bucket : buckets) {

			System.out.println(bucket.getName() + ": " + bucket.getCreationDate());

		}

	}

}
