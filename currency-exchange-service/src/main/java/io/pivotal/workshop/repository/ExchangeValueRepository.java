package io.pivotal.workshop.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import io.pivotal.workshop.domain.*;
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
		ExchangeValue findByFromAndTo(String from, String to);
}