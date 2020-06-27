package com.geronimodesenvolvimentos.gameservice.graphql;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.geronimodesenvolvimentos.gameservice.model.Vector3;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewRemoteGameObjectServiceTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;
    @Test
    public void canCreateRemoteObject() throws IOException{
        GraphQLResponse response = graphQLTestTemplate.postForResource("newRemoteGameObject.graphql");
        assertTrue(response.isOk());
        String id = response.get("$.data.newRemoteGameObject.id");
        Vector3 pos = response.get("$.data.newRemoteGameObject.position", Vector3.class);
        Vector3 speed = response.get("$.data.newRemoteGameObject.speed", Vector3.class);
        Vector3 orientation = response.get("$.data.newRemoteGameObject.orientation", Vector3.class);
        String prefabType = response.get("$.data.newRemoteGameObject.prefabType");
        assertTrue(id != null);
        assertTrue(pos.equals(new Vector3(1.1f, 2.2f, 3.3f)));
        assertTrue(speed.equals(new Vector3(-1, -2, -3)));
        assertTrue(orientation.equals(new Vector3(1,2,3)));
        assertTrue(prefabType.equals("shotgun"));
    }
}