package kodlama.io.rentacar.business.dto.responses.maintenance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import kodlama.io.rentacar.entities.enums.State;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnMaintenanceResponse {
    private int carId;
    private State state;
}
