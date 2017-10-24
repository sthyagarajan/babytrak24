package com.babytrak24.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babytrak24.model.AWSKeyPair;

public interface AWSKeyPairService extends JpaRepository<AWSKeyPair, Long> {

}
