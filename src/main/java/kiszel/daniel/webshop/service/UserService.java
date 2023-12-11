package kiszel.daniel.webshop.service;

import kiszel.daniel.webshop.DTO.UserDTO;
import kiszel.daniel.webshop.model.Address;
import kiszel.daniel.webshop.model.User;
import kiszel.daniel.webshop.repository.AddressRepository;
import kiszel.daniel.webshop.repository.UserRepository;
import kiszel.daniel.webshop.util.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Optional<User> getUser(int  userId) {
        return userRepository.findById(userId);
    }

    public Map<String, Integer> getUserIdByUsername(String name){
        Map<String, Integer> id = new HashMap<>();
        id.put("userId",userRepository.getUserIdByUsername(name));
        return id;
    }

    public void editUser(UserDTO editedUser) {
        Optional<User> user = userRepository.findById(editedUser.getId());
        Optional<Address> billingAddress = addressRepository.findById(editedUser.getId());
        Optional<Address> deliveryAddress = addressRepository.findById(editedUser.getId());

        if(user.isEmpty()){
            throw new BadRequestException("User Id is not good");
        }
        if(billingAddress.isEmpty()){
            throw new BadRequestException("BillingAddress Id is not good");
        }
        if(deliveryAddress.isEmpty()){
            throw new BadRequestException("DeliveryAddress Id is not good");
        }

        user.get().setUsername(editedUser.getUsername());
        user.get().setLastName(editedUser.getLastname());
        user.get().setFirstName(editedUser.getFirstname());

        billingAddress.get().setZipCode(editedUser.getBillingAddress().getZipcode());
        billingAddress.get().setCity(editedUser.getBillingAddress().getCity());
        billingAddress.get().setStreet(editedUser.getBillingAddress().getStreet());
        billingAddress.get().setHouseNumber(editedUser.getBillingAddress().getHouseNumber());
        billingAddress.get().setDoor(editedUser.getBillingAddress().getDoor());
        billingAddress.get().setFlat(editedUser.getBillingAddress().getFlat());
        billingAddress.get().setStairs(editedUser.getBillingAddress().getStairs());

        deliveryAddress.get().setZipCode(editedUser.getDeliveryAddress().getZipcode());
        deliveryAddress.get().setCity(editedUser.getDeliveryAddress().getCity());
        deliveryAddress.get().setStreet(editedUser.getDeliveryAddress().getStreet());
        deliveryAddress.get().setHouseNumber(editedUser.getDeliveryAddress().getHouseNumber());
        deliveryAddress.get().setDoor(editedUser.getDeliveryAddress().getDoor());
        deliveryAddress.get().setFlat(editedUser.getDeliveryAddress().getFlat());
        deliveryAddress.get().setStairs(editedUser.getDeliveryAddress().getStairs());

        addressRepository.save(billingAddress.get());
        addressRepository.save(deliveryAddress.get());
        userRepository.save(user.get());
    }
}
