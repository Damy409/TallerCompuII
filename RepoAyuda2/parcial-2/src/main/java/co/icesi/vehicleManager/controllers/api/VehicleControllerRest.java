package co.icesi.vehicleManager.controllers.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.vehicleManager.dtos.VehicleDto;
import co.icesi.vehicleManager.mappers.VehicleMapper;
import co.icesi.vehicleManager.model.Vehicle;
import co.icesi.vehicleManager.services.impl.VehicleServiceImp;


@RestController
@RequestMapping("/vehicle")
public class VehicleControllerRest implements VehicleController{

    @Autowired
    private VehicleServiceImp vehicleService;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    @GetMapping
    public ResponseEntity<List<VehicleDto>> findAllVehicle() {
        List<Vehicle> vehicles=vehicleService.getAllVehicle();
        List<VehicleDto> vehiclesDto=vehicles.stream().map(vehicleMapper::vehicleToVehicleDto).collect(Collectors.toList());
        return ResponseEntity.ok(vehiclesDto);
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE_VEHICLE')")
    @PostMapping
    public ResponseEntity<VehicleDto> addVehicle(@RequestBody VehicleDto dto) {
        Vehicle vehicle=vehicleMapper.vehicleDtoToVehicle(dto);
        vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(201).body(vehicleMapper.vehicleToVehicleDto(vehicle));
    }

    @Override
    @PreAuthorize("hasAuthority('UPDATE_VEHICLE')")
    @PutMapping
    public ResponseEntity<VehicleDto> updateVehicle(@RequestBody VehicleDto dto) {
        Vehicle vehicle=vehicleMapper.vehicleDtoToVehicle(dto);
        vehicleService.updateVehicle(vehicle);
        return ResponseEntity.ok().body(vehicleMapper.vehicleToVehicleDto(vehicle));
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_VEHICLE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable long id) {
        vehicleService.deleteVehicle(id);
       return ResponseEntity.ok().body("Se elimino correctamente");
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_VEHICLE')")
    public ResponseEntity<VehicleDto> findById(@PathVariable long id) {
        Vehicle vehicle=vehicleService.getVehicleById(id);

        return ResponseEntity.ok().body(vehicleMapper.vehicleToVehicleDto(vehicle));
    }
    
}
