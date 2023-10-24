package book.shop.dto.status;

import book.shop.model.Status;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StatusDto {
    @NotEmpty
    private Status.StatusName name;
}
