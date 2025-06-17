package yamen.marcketplace.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpCheckRequestDto {

    String email;

    String otp;
}
