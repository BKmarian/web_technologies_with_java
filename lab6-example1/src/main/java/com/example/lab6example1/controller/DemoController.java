package com.example.lab6example1.controller;

import com.example.lab6example1.exception.InvalidFoodProductException;
import com.example.lab6example1.model.FoodProduct;
import com.example.lab6example1.service.FoodProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("demoapp")
public class DemoController {

    private final FoodProductService service;

    public DemoController(FoodProductService service) {
        this.service = service;
    }

    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/helo")
    public String hello()   {
        return "hello :)";
    }

    @GetMapping(value = "/example1")
    public FoodProduct getFoodProduct() {
         FoodProduct cake = new FoodProduct();
         cake.setName("ChocolateCake");
         cake.setPrice(100);
         cake.setIngredients(List.of("sugar", "chocolate","orage"));

         return cake;
    }

    @GetMapping(value = "/example2")
    public List<FoodProduct> getFoodProducts() {
        FoodProduct cake = new FoodProduct();
        cake.setName("ChocolateCake");
        cake.setPrice(100);
        cake.setIngredients(List.of("sugar", "chocolate","orage"));

        FoodProduct bread = new FoodProduct();
        cake.setName("Bread");
        cake.setPrice(2);
        cake.setIngredients(List.of("sugar", "salt","seeds"));

        return Arrays.asList(cake, bread);
    }

    @GetMapping("/example3")
    public FoodProduct getFoodProductAndOtherData(HttpServletResponse response) {
        FoodProduct cake = new FoodProduct();
        cake.setName("ChocolateCake");
        cake.setPrice(100);
        cake.setIngredients(List.of("sugar", "chocolate","orage"));

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        return cake;
    }

    @GetMapping("/example4")
    public FoodProduct getFoodProductAndOtherHeader(HttpServletResponse response) {
        FoodProduct cake = new FoodProduct();
        cake.setName("ChocolateCake");
        cake.setPrice(100);
        cake.setIngredients(List.of("sugar", "chocolate","orage"));

        response.setHeader("name", "Student");
        response.setHeader("class", "WEB");
        return cake;
    }

    @GetMapping("/example5")
    public ResponseEntity changeResponseDemo()  {
        FoodProduct cake = new FoodProduct();
        cake.setName("ChocolateCake");
        cake.setPrice(100);
        cake.setIngredients(List.of("sugar", "chocolate","orage"));

        return ResponseEntity.ok(cake);
    }

    @GetMapping("/example6")
    public String example6(@RequestParam(required = false) String name)    {
        return "Hello " + name;
    }

    @GetMapping("/example7")
    public String example7(@RequestParam("nume") String name)    {
        return "Hello " + name;
    }

    @GetMapping("/example8")
    public String example8(@RequestParam String name,
                           @RequestParam String lastName)    {
        return "Hello " + name + " " + lastName;
    }

    @GetMapping("/example9")
    public Map<String, String> example9(@RequestParam Map<String, String> parameters)   {
        return parameters;
    }

    @GetMapping("/example10")
    public String example10(@RequestHeader String name)   {
        return "Ciao " + name;
    }

    @GetMapping("/example11")
    public Map<String, String> example11(@RequestHeader Map<String, String> headers)   {
        return headers;
    }

    @GetMapping("/example12/{name}")
    public String example12(@PathVariable String name)  {
        return "Bonjour " + name;
    }

    @GetMapping("/example13/{name}")
    public String example13(@PathVariable("name") String personalName)  {
        return "Bonjour " + personalName;
    }

    @GetMapping({"/example14/{name}", "/example14"})
    public String example14(@PathVariable(value = "name", required = false) String name)  {
        return "Bonjour " + name;
    }

    @GetMapping("/example15")
    public FoodProduct sendProduct(@RequestBody FoodProduct product)    {
        return product;
    }

    List<FoodProduct> products = new ArrayList<>();

    @PostMapping("/example16")
    public void createProduct(@RequestBody FoodProduct product){
        //logica de add product
        products.add(product);
    }

    @GetMapping("/example17")
    public List<FoodProduct> gettAllProducts()  {
        return products;
    }

    @GetMapping("/checkerror")
    public ResponseEntity checkErrors()   {
        try {
            return ResponseEntity.ok(service.isProductInvalid());
        } catch (InvalidFoodProductException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/checkerror2")
    public FoodProduct checkOtherException(){
        return service.generalExceptionWithProduct();
    }



}
