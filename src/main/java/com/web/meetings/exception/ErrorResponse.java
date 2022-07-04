package com.web.meetings.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class ErrorResponse {

		public ErrorResponse(String message, String status) {
			super();
			this.message = message;
			this.status = status;
		}

		@JsonProperty("message")
		private String message = null;

		@JsonProperty("status")
		private String status = null;
		
		
}
