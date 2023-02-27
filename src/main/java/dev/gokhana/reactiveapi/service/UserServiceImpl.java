package dev.gokhana.reactiveapi.service;

import dev.gokhana.reactiveapi.client.UserWebClient;
import dev.gokhana.reactiveapi.model.Address;
import dev.gokhana.reactiveapi.model.User;
import dev.gokhana.reactiveapi.repository.AddressRepository;
import dev.gokhana.reactiveapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserWebClient userWebClient;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserServiceImpl(UserWebClient userWebClient, UserRepository userRepository, AddressRepository addressRepository) {
        this.userWebClient = userWebClient;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Mono<User> getUserById(int id) {
        return userRepository.findById(id).flatMap(user -> {
            if (user.getAddressId() == null) return Mono.just(user);
            Mono<Address> addressMono = addressRepository.findById(user.getAddressId());
            return addressMono.map(address -> {
                user.setAddress(address);
                return user;
            });
        });
    }

    @Override
    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> saveUser(User userDTO) {
        if (userDTO.getAddress() == null) return userRepository.save(userDTO);
        return saveUserWithAddress(userDTO);
    }

    public Mono<User> saveUserWithAddress(User user) {
        return addressRepository.save(user.getAddress()).flatMap(address -> {
            user.setAddressId(address.getId());
            return userRepository.save(user);
        });
    }

    @Override
    public Mono<User> updateUser(int id, User userDTO) {
        return userRepository.findById(id).flatMap(user -> {
            userDTO.setId(user.getId()); // if there is something else to update do it here.
            return userRepository.save(userDTO);
        });
    }

    @Override
    public Mono<Void> deleteUser(int id) {
        return userRepository.deleteById(id);
    }


    @Override
    public Mono<User> getGuestUserById(int id) {
        return userWebClient.retrieveGuestUser(id);
    }
}
