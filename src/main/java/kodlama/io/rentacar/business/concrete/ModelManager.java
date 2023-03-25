package kodlama.io.rentacar.business.concrete;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import kodlama.io.rentacar.entities.Model;
import kodlama.io.rentacar.repository.ModelRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapper mapper;

    @Override
    public List<GetAllModelResponse> getAll() {
        List<GetAllModelResponse> response = modelRepository.findAll().stream()
                .map(model -> mapper.map(model,GetAllModelResponse.class)).toList();
        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExists(id);
        return mapper.map(modelRepository.findById(id),GetModelResponse.class);
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        modelRepository.save(model);
        return mapper.map(model,CreateModelResponse.class);
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        checkIfModelExists(id);
        Model model = mapper.map(request,Model.class);
        model.setId(id);
        modelRepository.save(model);
        return mapper.map(model,UpdateModelResponse.class);
    }

    @Override
    public void delete(int id) {
        checkIfModelExists(id);
        modelRepository.deleteById(id);

    }

    public void checkIfModelExists(int id) {
        if (!modelRepository.existsById(id)) throw new RuntimeException("Marka bulunamadı");
    }
}
