package co.icesi.vehicleManager.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.vehicleManager.model.User;
import co.icesi.vehicleManager.model.Vehicle;
import co.icesi.vehicleManager.repositories.UserRepository;
import co.icesi.vehicleManager.repositories.VehicleRepository;
import co.icesi.vehicleManager.services.interfaces.VehicleService;

@Service
public class VehicleServiceImp implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Vehicle createVehicle(Vehicle Vehicle) {
        return vehicleRepository.save(Vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle Vehicle) {
        return vehicleRepository.save(Vehicle);
    }

    @Override
    public void deleteVehicle(long VehicleId) {
        vehicleRepository.deleteById(VehicleId);
    }

    @Override
    public void assignVehicle(long VehicleId, long userId) {
        Optional<Vehicle> vehicleOpt= vehicleRepository.findById(VehicleId);
        Optional<User> userOpt= userRepository.findById(userId);

        if(vehicleOpt.isPresent()&&userOpt.isPresent()){
            Vehicle vehicle=vehicleOpt.get();
            User user=userOpt.get();
            if(user.getVehicles()==null){
                user.setVehicles(new ArrayList<>());
            }

            user.getVehicles().add(vehicle);
            userRepository.save(user);
        }

    }

    @Override
    public void unassignVehicle(long VehicleId, long userId) {
        Optional<Vehicle> vehicleOpt= vehicleRepository.findById(VehicleId);
        Optional<User> userOpt= userRepository.findById(userId);

        if(vehicleOpt.isPresent()&&userOpt.isPresent()){
            Vehicle vehicle=vehicleOpt.get();
            User user=userOpt.get();

            user.getVehicles().remove(vehicle);
            userRepository.save(user);
        }
    }

    @Override
    public Vehicle getVehicleById(long VehicleId) {
        return vehicleRepository.findById(VehicleId).get();
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }
    
}
