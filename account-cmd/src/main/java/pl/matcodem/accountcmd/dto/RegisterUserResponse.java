package pl.matcodem.accountcmd.dto;

import pl.matcodem.accountcore.dto.BaseResponse;

public class RegisterUserResponse extends BaseResponse {
    private final String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
