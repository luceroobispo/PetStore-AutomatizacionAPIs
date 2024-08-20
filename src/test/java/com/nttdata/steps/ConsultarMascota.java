package com.nttdata.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class ConsultarMascota {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_ENDPOINT = "/pet/{petId}";

    private io.restassured.response.Response response;

    @Step("Consultar mascota en base a su id")
    public void consultarMascota(int petId) {
        response = SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .when()
                .get(BASE_URL + PET_ENDPOINT, petId)
                .then()
                .extract().response();
    }

    @Step("Imprimir mascota")
    public void verificarMascotaExistente(int petId) {
        consultarMascota(petId);
        Assert.assertEquals(200, response.statusCode());

        Integer id = response.jsonPath().get("id");
        Assert.assertNotNull("Mascota no encontrada", id);

        String status = response.jsonPath().getString("status");
        Assert.assertNotEquals("Mascota está 'sold'", "sold", status);
    }
}
