package api.fleetManagementAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // Esta notación sirve para indicarle a Spring q esta clase es un controller, por lo que hay que agregarla a cualquier controller creado
public class HelloController {

    @GetMapping(path="/hello") //va a enlazar las peticiones GET a la URL indicada en el paréntesis consecutivo
    @ResponseBody // en q cambia usar o no esta anotacion?
    public String getHello() {

        return "JELOU! this is a GET request or whatttt";
    }
}
