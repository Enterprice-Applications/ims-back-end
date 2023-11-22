package lk.ijse.dep11.ims.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherTO implements Serializable {
    @Null(message = "Id should be empty")
    private Integer id;
    @NotBlank(message = "name should not be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid name")
    private String name;
    @NotBlank(message = "contact should not be empty")
    @Pattern(regexp = "^\\d{3}-\\d{7}$", message = "Invalid contact")
    private String contact;
}
