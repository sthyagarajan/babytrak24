package com.babytrak24.service;


import org.springframework.data.jpa.repository.JpaRepository;

import com.babytrak24.model.Baby;

public interface BabyService extends JpaRepository<Baby, Long> {

}
