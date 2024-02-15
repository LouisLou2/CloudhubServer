package com.leo.cloudhubserver;

import com.googlecode.protobuf.format.JsonFormat;
import com.leo.cloudhubserver.model.PersonProto.Person;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProtobufApplicationTests {

    private static final String URL = "http://localhost:8090/test";


    @Test
    void contextLoads() {
        
    }

    @Test
    public void getStudentsTest() throws IOException {
        InputStream responseStream = executeHttpRequest(URL);
        String jsonOutput = convertProtobufMessageStreamToJsonString(responseStream);
        System.out.println(jsonOutput);
        assertResponse(jsonOutput);
    }

    private String convertProtobufMessageStreamToJsonString(InputStream protobufStream) throws IOException {
        JsonFormat jsonFormat = new JsonFormat();
        Person per = Person.parseFrom((protobufStream));
        return jsonFormat.printToString(per);
    }

    private InputStream executeHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(request);
        return response.getEntity().getContent();
    }

    private void assertResponse(String response) {
        assertThat(response, containsString("1"));
        assertThat(response, containsString("denis"));
    }


}