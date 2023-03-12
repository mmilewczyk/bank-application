package pl.matcodem.bankacccmd.dto;

import lombok.Getter;
import lombok.Setter;
import pl.matcodem.bankacccore.dto.BaseResponse;

public class OpenBankAccountResponse extends BaseResponse {

    @Getter @Setter
    private String id;

    public OpenBankAccountResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
