package data.animal;

public class AnimalResponse {
	AnimalHeader header;
	AnimalBody body;
	
	@Override
	public String toString() {
		return "Aresponse [aheader=" + header + ", abody=" + body + "]";
	}

	public AnimalHeader getHeader() {
		return header;
	}

	public void setHeader(AnimalHeader aheader) {
		this.header = aheader;
	}

	public AnimalBody getBody() {
		return body;
	}

	public void setBody(AnimalBody abody) {
		this.body = abody;
	}
	
	
	
}
