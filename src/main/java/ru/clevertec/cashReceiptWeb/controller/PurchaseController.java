package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;
import ru.clevertec.cashReceiptWeb.util.MappingUtil;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final ProductService productService;
    private final UserService userService;
    private final PurchaseService purchaseService;
    private final DiscountCardService discountCardService;
    private final OrderService orderService;
    private final MappingUtil mappingUtil;

    @Autowired
    public PurchaseController(ProductService productService, UserService userService,
                              PurchaseService purchaseService, DiscountCardService discountCardService,
                              OrderService orderService, MappingUtil mappingUtil) {
        this.productService = productService;
        this.userService = userService;
        this.purchaseService = purchaseService;
        this.discountCardService = discountCardService;
        this.orderService = orderService;
        this.mappingUtil = mappingUtil;
    }

//    @GetMapping("/products")
//    public String showProducts(Model model) {
//        model.addAttribute("purchase", new Purchase());
//        model.addAttribute("products", productService.findAllProducts());
//        model.addAttribute("discount", GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE);
//        return "purchase/byProductPage";
//    }

    @PostMapping
    public PurchaseSimpleResponseDto addPurchase(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        return purchaseService.addPurchase(purchaseRequestDto);
    }

    @GetMapping()
    public List<PurchaseSimpleResponseDto> getAllPurchasesDto() {
        User user = userService.getCurrentUser();
        return purchaseService.getAllPurchasesSimpleResponseDtoByUserId(user.getId());
    }

//    @GetMapping()
//    public List<ProductResponseDto> getAllProductsDto() {
//        return productService.getAllProductsResponseDto();
//    }

//    @GetMapping("/{id}")
//    public PurchaseSimpleResponseDto getPurchaseDto(@PathVariable Logn)


//    @GetMapping("/{id}")
//    public ProductResponseDto getProductDto(@PathVariable Long id) {
//        return productService.getProductResponseDto(id);
//    }
//
//    @PostMapping("/buy")
//    public String buy(@ModelAttribute(value = "purchase") Purchase purchase) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();
//
//        purchase.setUserId(user.getId());
//        purchaseService.save(purchase);
//        return "redirect:/purchase/products";
//    }
//
//    @GetMapping("/cart")
//    public String cart(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();
//
//        DiscountCard discountCard = discountCardService.findDiscountCardByCardNumber(user.getCardNumber()).orElseThrow();
//        List<PurchaseFullResponseDto> purchasesDto = purchaseService.getCurrentUserPurchaseFullResponseDtoList();
//        PurchaseCostDto purchaseCostDto = orderService.getCurrentUserPurchasesCostDto();
//        PurchaseCostViewDto purchaseCostViewDto = mappingUtil.mapToToPurchaseCostViewDto(purchaseCostDto);
//
//        model.addAttribute("purchasesDto", purchasesDto);
//        model.addAttribute("purchaseCostViewDto", purchaseCostViewDto);
//        model.addAttribute("discountCard", discountCard);
//        return "purchase/cartPage";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deletePurchase(@PathVariable(value = "id") Long productId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();
//        purchaseService.deletePurchaseByPurchaseId(new PurchaseId(user.getId(), productId));
//        return "redirect:/purchase/cart";
//    }

}
