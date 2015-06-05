package bg.uni_sofia.fmi.javaee.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonProvider<T> implements MessageBodyReader<T>, MessageBodyWriter<T> {
	
	private Gson gson = GsonFactory.build();

	@Override
	public long getSize(T arg0, Class<?> arg1, Type arg2, Annotation[] arg3,
			MediaType arg4) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return true;
	}

	@Override
	public void writeTo(T arg0, Class<?> arg1, Type arg2, Annotation[] arg3,
			MediaType arg4, MultivaluedMap<String, Object> arg5,
			OutputStream arg6) throws IOException {
		try(PrintWriter printWriter = new PrintWriter(arg6);) {
			String json = gson.toJson(arg0);
			printWriter.write(json);
			printWriter.flush();
		}
	}

	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return true;
	}

	@Override
	public T readFrom(Class<T> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3, MultivaluedMap<String, String> arg4,
			InputStream arg5) throws IOException {
		try(InputStreamReader reader = new InputStreamReader(arg5, "UTF-8");){
			return gson.fromJson(reader, arg0);
		}
		
	}
	
}
