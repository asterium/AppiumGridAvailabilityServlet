package org.dimazay.selenium.grid.hub.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class MessageBuilder {
    private HttpServletResponse response;
    private Logger log;
    public MessageBuilder(HttpServletResponse response){
        this.response = response;
    }

    public MessageBuilder(HttpServletResponse response, Logger log) {
        this.response = response;
        this.log = log;
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
            log.log(Level.WARNING, "failed to write response message");
            e.printStackTrace();
        }
    }
}
