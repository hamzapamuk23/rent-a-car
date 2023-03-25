package kodlama.io.rentacar.business.dto.responses.get;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import kodlama.io.rentacar.entities.enums.State;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    @Enumerated(EnumType.STRING)
    private State state;
    private int modelId;
}
