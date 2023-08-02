package data.animal;

public class AnimalResponseResult {
	AnimalResponse response;

	@Override
	public String toString() {
		return "AnimalResponse [aresponse=" + response + "]";
	}

	public AnimalResponse getResponse() {
		return response;
	}

	public void setResponse(AnimalResponse aresponse) {
		this.response = aresponse;
	}
	
	
}
