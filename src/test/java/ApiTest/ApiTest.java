package ApiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiTest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";

    }

    @Test
    public void deveRetornarTarefas(){
        given()
        .when()
                .get("/todo")
        .then()
                .statusCode(200)
        ;
    }

    @Test
        public void deveAdicionarTarefaComSuceso(){

            given()
                    .body(" {\"task\": \"Teste via API\",\"dueDate\": \"2021-10-10\"}")
                    .contentType(ContentType.JSON)
                    .when()
                    .post("/todo")
                    .then()
                    .statusCode(201)
            ;
    }

    @Test
    public void NaoDeveAdicionarTarefaInvalida() {
        given()
                .body(" {\"task\": \"Teste via API\",\"dueDate\": \"2011-10-10\"}")
                .contentType(ContentType.JSON)
        .when()
                .post("/todo")
        .then()
                .statusCode(400)
                .body("message", is("Due date must not be in past"))
        ;
    }

}
