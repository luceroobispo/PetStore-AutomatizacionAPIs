package com.nttdata.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class CrearOrden {

    private static String CREATE_ORDER = "https://petstore.swagger.io/v2/store/order";
    private Response response;

    @Step("Crear orden de compra en PetStore")
    public void crearOrden(int id, int petId, int quantity, String shipDate, String status, boolean complete) {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"id\": \""+id+"\",\n" +
                        "  \"petId\": \""+petId+"\",\n" +
                        "  \"quantity\":  \""+quantity+"\",\n" +
                        "  \"shipDate\": \""+shipDate+"\",\n" +
                        "  \"status\": \""+status+"\",\n" +
                        "  \"complete\": \""+complete+"\"\n" +
                        "}")
                .log().all()
                .post(CREATE_ORDER)
                .then()
                .log().all()
        ;
    }

    @Step("Validar código de respuesta")
    public void validarCodigoRespuesta(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    @Step("Validar cuerpo de la respuesta")
    public void validarBodyRespuesta(String expectedBody) {
        String actualBody = response.getBody().asString();
        Assert.assertEquals("El cuerpo de la respuesta no coincide", expectedBody, actualBody);
    }
}
