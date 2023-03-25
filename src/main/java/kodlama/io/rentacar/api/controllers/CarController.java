package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.maintenance.ReturnMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.maintenance.SendMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    @GetMapping
    public List<GetAllCarsResponse> getAll() {
        return carService.getAll();
    }
    @GetMapping("status/{status}")
    public List<GetAllCarsResponse> getAllByState(@PathVariable("status") Boolean filter)
    {
        return carService.getAllByState(filter);
    }
    @GetMapping("{id}")
    public GetCarResponse getById(@PathVariable int id) {
        return carService.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@RequestBody CreateCarRequest request) {
        return carService.add(request);
    }
    @PutMapping("{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request) {
        return carService.update(id,request);
    }
    @PutMapping("/sendMaintenance/{id}")
    public SendMaintenanceResponse sendMaintenance(@PathVariable int id) {
        return carService.sendMaintenance(id);
    }

    @PutMapping("/returnMaintenance/{id}")
    public ReturnMaintenanceResponse returnMaintenance(@PathVariable int id) {
        return carService.returnMaintenance(id);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable int id) {
        carService.delete(id);
    }
}
