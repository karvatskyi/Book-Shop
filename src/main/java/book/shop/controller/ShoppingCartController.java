package book.shop.controller;

import book.shop.dto.book.BookDto;
import book.shop.model.ShoppingCart;
import book.shop.service.shoppingcart.ShoppingCartService;
import book.shop.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart management", description = "Endpoints for managing shopping cart")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/shopping_cart")
public class ShoppingCartController {

    private final UserService userService;

    private final ShoppingCartService shoppingCartService;

    @GetMapping(value = "/getCart")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get shopping cart", description = "Get available shopping cart")
    ShoppingCart getUserShoppingCart(Long id) {
        return userService.findUserById(id).getShoppingCart();
    }

    @PostMapping("/addToCart")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Add book to cart", description = "Add book to shopping cart")
    ShoppingCart addItemToShoppingCart(Long bookId, Long userId, int quantity) {
        return shoppingCartService.addItemToShoppingCart(userId, bookId, quantity);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    ShoppingCart updateQuantityInShoppingCart(int quantity, Long userId, Long itemId) {
        return shoppingCartService.updateQuantityInShoppingCart(quantity, userId, itemId);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    @Operation(summary = "Delete a book", description = "Delete a book from shopping cart")
    void deleteBookFromShoppingCart(Long userId, BookDto bookDto) {

    }
}
