package com.nttdata.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ConsultarOrden {

    private static String URL = null;
    Response response;

    @Step("Definir URL del servicio")
    public void setBaseUrl(String url) {
        URL = url;
    }

    @Step("Consultar orden de compra en base a su id")
    public void consultarOrden(int orderId) {
        response = SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .when()
                .get(URL + orderId)
                .then()
                .extract().response();
    }

    @Step("Imprimir orden de compra")
    public void imprimirOrden() {
        System.out.println("Orden de compra: " + response.asString());
    }
}
