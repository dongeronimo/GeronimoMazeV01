package com.geronimodesenvolvimentos.gameservice.graphql;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.geronimodesenvolvimentos.gameservice.management.RemoteGameObjectManagement;
import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.model.Vector3;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetRemoteGameObjectByIdTests {
    @Autowired
    private RemoteGameObjectManagement manager;
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;
    @Test
    void canFetch() throws IOException{
        //1)Insere no db
        RemoteGameObject go= manager.createNew(new Vector3(1,2,3), new Vector3(-3,-2,-1), new Vector3(0,1,0), "foo", null);
        //2)Tenta buscar
        ObjectNode parameters = new ObjectMapper().createObjectNode();
        parameters.put("id", go.getId());
        GraphQLResponse response = graphQLTestTemplate.perform("getRemoteGameObjectById.graphql", parameters);
        assertTrue(response.isOk());
        RemoteGameObject go2 = response.get("$.data.getRemoteGameObjectById", RemoteGameObject.class);
        //3)Valida
        assertTrue(go2!=null);
    }
}