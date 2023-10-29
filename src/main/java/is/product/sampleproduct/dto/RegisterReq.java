package is.product.sampleproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author "Noverry Ambo"
 * @start 10/25/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterReq {

    private String firstname;
    private String lastname;
    private String password;
    private String email;
}
