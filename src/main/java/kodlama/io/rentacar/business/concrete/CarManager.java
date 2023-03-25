package kodlama.io.rentacar.business.concrete;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.maintenance.ReturnMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.maintenance.SendMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.repository.CarRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllCarsResponse> getAllByState(Boolean filter) {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarsResponse> responses;
        if (filter == false)
        {
            responses = cars.stream().map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
        }
        else {
            responses = cars.stream().filter(car -> car.getState().toString() == State.AVAILABLE.name())
                    .map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
        }
        return responses;
    }

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarsResponse> responses = cars.stream()
                .map(car -> mapper.map(car,GetAllCarsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExists(id);
        return mapper.map(carRepository.findById(id).orElseThrow(), GetCarResponse.class);
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request,Car.class);
        car.setId(0);
        carRepository.save(car);
        return mapper.map(car,CreateCarResponse.class);
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfCarExists(id);
        Car car = mapper.map(request,Car.class);
        car.setId(id);
        carRepository.save(car);
        return mapper.map(car,UpdateCarResponse.class);
    }

    @Override
    public SendMaintenanceResponse sendMaintenance(int id) {
        checkIfCarExists(id);
        Car car = carRepository.findById(id).orElseThrow();
        validateMaintenance(car);
        car.setState(State.MAINTANCE);
        carRepository.save(car);
        SendMaintenanceResponse response = mapper.map(car,SendMaintenanceResponse.class);
        return response;
    }

    @Override
    public ReturnMaintenanceResponse returnMaintenance(int id) {
        checkIfCarExists(id);
        Car car = carRepository.findById(id).orElseThrow();
        car.setState(State.AVAILABLE);
        carRepository.save(car);
        ReturnMaintenanceResponse response = mapper.map(car,ReturnMaintenanceResponse.class);
        return response;
    }


    @Override
    public void delete(int id) {
        checkIfCarExists(id);
        carRepository.deleteById(id);

    }

    public void validateMaintenance(Car car) {
        checkIfCarRentedforMaintenance(car);
        checkIfCarAvailableforMaintenance(car);
    }

    public void checkIfCarAvailableforMaintenance(Car car) {
        if (car.getState().equals(State.MAINTANCE)) throw new RuntimeException("Araç zaten bakımda");
    }

    public void checkIfCarRentedforMaintenance(Car car) {
        if (car.getState().equals(State.RENTED)) throw new RuntimeException("Araç şuan kirada");
    }

    public void checkIfCarExists(int id) {
        if (!carRepository.existsById(id)) throw new RuntimeException("Marka bulunamadı");
    }
}
