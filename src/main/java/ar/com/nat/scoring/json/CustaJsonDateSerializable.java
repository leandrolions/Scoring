package ar.com.nat.scoring.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustaJsonDateSerializable  extends JsonSerializer<Date>{
	
    @Override
    public void serialize(Date date,JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
    	if(date == null) {
    		gen.writeNull();
    	}
    	else {
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        gen.writeString(format.format(date));

    	}

    }

}
