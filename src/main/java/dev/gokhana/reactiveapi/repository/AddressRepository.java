package dev.gokhana.reactiveapi.repository;

import dev.gokhana.reactiveapi.model.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {

}