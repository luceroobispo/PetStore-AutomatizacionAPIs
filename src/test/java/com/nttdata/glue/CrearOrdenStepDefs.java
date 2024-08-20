package com.nttdata.glue;

import com.nttdata.steps.CrearOrden;
import com.nttdata.steps.ConsultarMascota;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CrearOrdenStepDefs {
    @Steps
    CrearOrden crearOrden;

    @Steps
    ConsultarMascota mascota;

    @Given("consulto si la mascota con id {int} existe y no está con estado sold")
    public void consultoSiLaMascotaConIdExiste(int petId) {
        mascota.verificarMascotaExistente(petId);
    }

    @When("creo la orden de compra con id {int}, petId {int}, quantity {int}, shipDate {string}, status {string} y complete {string}")
    public void crearOrden(int id, int petId, int quantity, String shipDate, String status, String completeStr) {
        boolean complete = Boolean.parseBoolean(completeStr);

        crearOrden.crearOrden(id, petId, quantity, shipDate, status, complete);
    }

    @Then("el código de respuesta es {int}")
    public void elCódigoDeRespuestaEs(int statusCode) {
        crearOrden.validarCodigoRespuesta(statusCode);
    }

    @And("el body de la response coincide con el body de la orden de compra creada")
    public void validoQueElBodyDeLaRespuestaSeaIgualA(String body) {
        crearOrden.validarBodyRespuesta(body);
    }
}
