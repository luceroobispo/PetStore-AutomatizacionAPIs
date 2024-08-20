Feature: Crear y consultar orden de compra para una mascota en PetStore

  @crearOrdenCompra
  Scenario Outline: Crear orden de compra
    Given consulto si la mascota con id <petId> existe y no está con estado sold
    When creo la orden de compra con id <id>, petId <petId>, quantity <quantity>, shipDate "<shipDate>", status "<status>" y complete "<complete>"
    Then el código de respuesta es <statusCode>
    And el body de la response coincide con el body de la orden de compra creada
      """
      {
        "id": <id>,
        "petId": <petId>,
        "quantity": <quantity>,
        "shipDate": "<shipDate>",
        "status": "<status>",
        "complete": <complete>
      }
      """

    Examples:
     | id | petId | quantity | shipDate   | status    | complete | statusCode |
     | 1  | 1     | 1        | 2024-08-16 | placed    | true     | 200        |
     | 2  | 2     | 1        | 2024-08-17 | approved  | false    | 200        |
     | 3  | 3     | 1        | 2024-08-18 | delivered | true     | 200        |


  @consultarOrdenCompra
  Scenario Outline: Consultar orden de compra existente
    Given la url del servicio es "https://petstore.swagger.io/v2/store/order/"
    When consulto si existe la orden de compra con id <orderId>
    Then el código de respuesta es <statusCode>
    And imprimo el orden de compra

    Examples:
        | orderId | statusCode |
        | 1       | 200        |
        | 2       | 200        |
        | 3       | 200        |