package com.nttdata.glue;

import com.nttdata.steps.ConsultarOrden;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;


public class ConsultarOrdenStepDefs {
    @Steps
    ConsultarOrden consultarOrden;

    @Given("la url del servicio es {string}")
    public void setBaseUrl(String url) {
        consultarOrden.setBaseUrl(url);
    }

    @When("consulto si existe la orden de compra con id {int}")
    public void consultarOrden(int orderId) {
        consultarOrden.consultarOrden(orderId);
    }

    @Then("imprimo el orden de compra")
    public void imprimirOrden() {
        consultarOrden.imprimirOrden();
    }

    @Then("el código de respuesta es statusCode {int}")
    public void elCódigoDeRespuestaEsStatusCode(int statusCode) {
        consultarOrden.validarCodigoRespuesta(statusCode);
    }
}