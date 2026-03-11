package yamen.marcketplace.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpCheckRequestDto {

    String email;

    String otp;
}
