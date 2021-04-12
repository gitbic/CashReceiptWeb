package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


//    // if username exist return errorMsg
//    // if id exist this method save user, but id must be autoincrement
//    @PostMapping()
//    public User addUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
//    //  if user not exist ? addUser : error
//    @PutMapping("/{id}")
//    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
//        return userService.updateUser(id, newUser);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUserById(id);
//    }
//
//    @GetMapping()
//    public List<User> getAllUsers() {
//        return userService.findAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable Long id) {
//        return userService.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
//    }

//    @PostMapping("/add")
//    public String addProduct(@ModelAttribute(value = "product") Product product) {
//        productService.save(product);
//        return "redirect:/admin/productManager";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable(value = "id") Long id) {
//        productService.deleteById(id);
//        return "redirect:/admin/productManager";
//    }
//
//    @GetMapping("/view/{id}")
//    public String viewProduct(Model model, @PathVariable(value = "id") Long id) {
//        Product product = productService.findById(id).orElseThrow();
//        model.addAttribute("discount", DISCOUNT_PERCENT_FOR_PURCHASE);
//        model.addAttribute(product);
//        return "product/productViewPage";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String productManager(Model model, @PathVariable(value = "id") Long id) {
//        Product product = productService.findById(id).orElseThrow();
//        model.addAttribute("product", product);
//        return "product/productEditPage";
//    }
//
//    @PostMapping("/update")
//    public String updateProduct(@ModelAttribute(value = "product") Product product) {
//        productService.save(product);
//        return "redirect:/admin/productManager";
//    }

}
