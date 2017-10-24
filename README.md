BabyTrak24 App
======================

The repository contains the code for babytrak24app, written leveraging Spring Boot and deployed AWS
It is a custom application built to showcase the ability to monitor baby activity round the clock


## Table of content

- [Introduction](#introduction)
    - [Idea](#idea)
    - [Solution](#solution)
    - [Features](#features)
    - [Screenshots](#screenshots)
- [Prerequisites](#prerequisites)
    - [AWSComponents](#awscomponents)
    - [LocalSetup](#localsetup)
    - [Frameworks](#frameworks)
    - [Runconfig](#runconfig)
- [Profile](#profile)
- [Links](#links)

## Introduction
### Idea

New born and kids until they become teens are to be monitored with great care. This includes their feeding cycles, pee, poop, sleep time, medications along with innumerous other details. All parents especially those of new born are going through a rigorous, round the clock cycle to manage their feeding and excretion cycles. Managing this information is not easy and at times due to physical and mental pressure people tend to forget. So, we need an easy way to manage all baby details securely and be able to retrieve it on demand while interacting with doctors

### Solution

We will build an application that will provide easy capture of data and retrieval of the critical day to day activities of the babies and kids. The end goal of the application is to be able to talk to home automation tools or using mobile app to capture activity details as when it happens. The application will have easy way retrieve the information and share it with different stake holders, such as doctors, pharmacist, retailers on a need basis. This could also potential help with health care in analyzing baby activities to potential identify illness earlier than it’s too late

### Features

- Registration
- Baby Profile management
- Upload photos
- Log feed, pee, poop
- Manage physical records such as weight

### Screenshot

<img src="https://github.com/sthyagarajan/babytrak24/blob/master/BabyTrak24App.png"/>

## Prerequisites

### AWSComponents

- EC2
- ELB
- Lambda
- AutoScaling Group
- Single AZ RDS 
- CloudFront
- S3
- S3 Transfer Acceleration
- R53
- CloudWatch
- SNS
- AWS SDK

### LocalSetup

Tools that need to be installed in local environment

- Maven
- PostgreSQL
- JDK
- IDE (Eclipse/IntelliJ IDEA)
- Tomcat

### Frameworks

- Spring Boot
- Spring MVC
- Spring JPA
- Bootstrap
- jquery

### Runconfig

We need to checkout the repository code, update the application.properties to update the database connection url, username and password.
Update the ImageService.java file and update AWSSDK access key pair as downloaded from AWS

Run the following in the project root folder, to deploy to maven repository

```
    $ mvn clean install
```

You can import the project as a maven in Eclipse or IntelliJ IDEA and run the application as SpringBoot App

You can run the application using the following maven command

```
    $ mvn spring-boot:run
```

## Profile

- University Name: http://www.sjsu.edu/ 
- Course: Cloud Technologies
- Professor Sanjay Garje 
- ISA: Divyankitha Urs
- Student: Sunder Thyagarajan  
- LinkedIn:<a href="https://www.linkedin.com/in/sunderthyagarajan/">https://www.linkedin.com/in/sunderthyagarajan</a>


## Links

* [Web site](https://babytrak24.com)
* [Source code](https://github.com/sthyagarajan/babytrak24)
