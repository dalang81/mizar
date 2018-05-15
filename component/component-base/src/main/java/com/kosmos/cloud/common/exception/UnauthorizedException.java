package com.kosmos.cloud.common.exception;

import com.kosmos.cloud.common.code.ResponseCode;

public class UnauthorizedException extends CommonException {

    private static final long serialVersionUID = 2234821080173990684L;

    public UnauthorizedException(String code, String message) {
        super(code, message);
    }

    public UnauthorizedException() {
        this(ResponseCode.NOT_AUTHORIZED);
    }

	public UnauthorizedException(ResponseCode repsCode) {
		this(repsCode.getCode(),repsCode.getMessage());
	}

}
