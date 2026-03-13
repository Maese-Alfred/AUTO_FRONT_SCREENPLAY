Feature: Envio de Kudos entre colaboradores
  Como colaborador de la organizacion
  Quiero enviar un kudo a un compañero
  Para reconocer su excelente trabajo

  Scenario: Enviar un kudo exitosamente
    Given que el colaborador se encuentra en la pagina principal
    When accede a la sección de Kudos
    And selecciona como remitente "christopher@sofkianos.com"
    And selecciona como destinatario "santiago@sofkianos.com"
    And selecciona la categoría "Teamwork"
    And escribe el mensaje "Excelente trabajo en el sprint Screenplay"
    And desliza el slider para enviar el kudo
    Then el kudo con mensaje "Excelente trabajo en el sprint Screenplay" aparece en la lista de kudos
