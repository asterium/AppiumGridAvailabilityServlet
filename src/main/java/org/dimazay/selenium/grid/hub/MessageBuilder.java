package org.dimazay.selenium.grid.hub;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class MessageBuilder {
    HttpServletResponse response;
    public MessageBuilder(HttpServletResponse response){
        this.response = response;
    }

    public void buildResponseMessage(Object data){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);

        Writer writer = null;
        try {
            writer = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            String payload = mapper.writeValueAsString(data);

           writer.write(payload);
            writer.flush();

        } catch (IOException e) {

        }
    }
}
