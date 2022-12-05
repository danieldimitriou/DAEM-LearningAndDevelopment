package gr.athtech.daem.transfer;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Value
@Builder
public class ApiResponse<T> implements Serializable {

	transient T data;

	ApiError apiError;

	LocalDateTime timestamp = LocalDateTime.now();
}
