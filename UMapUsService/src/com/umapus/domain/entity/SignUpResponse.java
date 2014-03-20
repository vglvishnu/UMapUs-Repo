package com.umapus.domain.entity;

public enum SignUpResponse {

	SUCCESS{

		@Override
		public String getStatus() {
			
			return "success";
		}},
	ALREADY_EXISTS{

		@Override
		public String getStatus() {
			
			return "user_already_exists";
		}}, 
	FAILED{

		@Override
		public String getStatus() {
			
			return "failed";
		}};

	
	
	public abstract String getStatus();

}
