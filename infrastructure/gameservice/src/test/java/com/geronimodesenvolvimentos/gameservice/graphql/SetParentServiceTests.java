package com.geronimodesenvolvimentos.gameservice.graphql;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.geronimodesenvolvimentos.gameservice.management.RemoteGameObjectManagement;
import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.model.Vector3;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SetParentServiceTests {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;
    @Autowired
    private RemoteGameObjectManagement remoteGameObjectManagement;
    private RemoteGameObject parent;
    private RemoteGameObject child;
    @BeforeEach
    public void setUp(){
        parent = remoteGameObjectManagement.createNew(new Vector3(), new Vector3(), new Vector3(), "foo", null);
        child = remoteGameObjectManagement.createNew(new Vector3(), new Vector3(), new Vector3(), "bar", null);
    }
    @Test
    public void canSetParent() throws IOException{
        ObjectNode parameters = new ObjectMapper().createObjectNode();
        parameters.put("parentId", parent.getId());
        parameters.put("childId", child.getId());
        GraphQLResponse response = graphQLTestTemplate.perform("setParent.graphql", parameters);
        assertTrue(response.isOk());
        RemoteGameObject returnedParent = response.get("$.data.setParent", RemoteGameObject.class);
        assertTrue(returnedParent!=null);
        assertTrue(returnedParent.getChildren().size()==1);
        RemoteGameObject childInParent = returnedParent.getChildren().stream().filter(go->go.equals(child)).findFirst().get();
        assertTrue(childInParent.getParent().equals(parent));
    }
}