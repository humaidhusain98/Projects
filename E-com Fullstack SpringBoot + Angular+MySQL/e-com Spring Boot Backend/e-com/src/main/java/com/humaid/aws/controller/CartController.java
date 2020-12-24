package com.humaid.aws.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humaid.aws.model.Address;
import com.humaid.aws.model.CartItem;

import com.humaid.aws.model.OrderItem;
import com.humaid.aws.model.Product;
import com.humaid.aws.model.Status;
import com.humaid.aws.model.paymentMode;
import com.humaid.aws.service.AddressService;
import com.humaid.aws.service.CartService;
import com.humaid.aws.service.CustomUserDetails;
import com.humaid.aws.service.OrderService;
import com.humaid.aws.service.ProductService;


@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private OrderService orderService;
	
	@Autowired 
	private AddressService addressService;
	
	@GetMapping
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<List<CartItem>> getCartByUserid() {
		CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("All Carts Displayed By User Id--Controller CLass");
		return ResponseEntity.ok().body(cartService.getCartItemsByUserIdAndOrderId(user.getUser().getUser_id()));
	}
	
	@GetMapping("/add/{productid}")
	@CrossOrigin(origins="http://localhost:4200")
   public void addProductToCart(@PathVariable Long productid) {
	   CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   CartItem obj=cartService.getCartItemsByProductIdAndUserId(productid,user.getUser().getUser_id());
	   Product productobj=productService.getProduct(productid);
	   if(obj==null) {
		   obj=new CartItem(productid, user.getUser().getUser_id(), 1,productobj.getPrice(),productobj.getName(),null);
		   cartService.save(obj);
		   System.out.println("Creating new Cart Object");
		  }
	   else {
		   obj.setQty(obj.getQty()+1);
		   cartService.edit(obj);
		   System.out.println("Modifying Existing Cart Object");
	   }
	   
	   
   }
   @GetMapping("/remove/{productid}")
   @CrossOrigin(origins="http://localhost:4200")
   public void removeProductFromCart(@PathVariable Long productid) {
	   CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   CartItem obj=cartService.getCartItemsByProductIdAndUserId(productid,user.getUser().getUser_id());
	   if(obj==null) {
		   System.out.println("Cart Object With Product-Id and User Id doesnt exist");
		   }
	   if(obj.getQty()==1) {
		   System.out.println("Cart Object Deleted as qty=1");
		   cartService.delete(obj);
	   }
	   if(obj.getQty()>1) {
		   System.out.println("Decrementing Qty -1");
		   obj.setQty(obj.getQty()-1);
		   cartService.edit(obj);
	   }
	   
   }
   
   @GetMapping("/total")
   @CrossOrigin(origins="http://localhost:4200")
   public ResponseEntity<Integer>getCartTotal(){
	   int total=0;
	   List<CartItem> cartlist=getCartByUserid().getBody();
	   for(int i=0;i<cartlist.size();i++)
	   	{	
		   total=total+cartlist.get(i).getQty()*cartlist.get(i).getPrice();
	   	}
	   

	   return ResponseEntity.ok().body(total);
   }
   
   @GetMapping("/{id}")
   @CrossOrigin(origins="http://localhost:4200")
   public Product getProduct(@PathVariable Long id) {
	   Product objProduct=productService.getProduct(id);
	   return objProduct;
   }
   
   @GetMapping("/address")
   @CrossOrigin(origins="http://localhost:4200")
   public ResponseEntity<List<Address>> getUserAddress() {
	   CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   List<Address> list=addressService.getAddressByUserId(user.getUser().getUser_id());
	   return ResponseEntity.ok().body(list);
	 }
   
   @PostMapping("/address")
   @CrossOrigin(origins="http://localhost:4200")
   public void addAddress(@RequestBody Address address) {
	   System.out.println(address);
	   CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   address.setUserid(user.getUser().getUser_id());
	   addressService.save(address);
	   
   }
   
   @PostMapping("/deleteaddress")
   @CrossOrigin(origins="http://localhost:4200")
   public void deleteAddressByUser(@RequestBody Address addr) {
	   addr.setUserid(-9999);
	   addressService.save(addr);
	   
	   
   }
   
   
  
   
   
   
   @GetMapping("/address/{addressid}")
   @CrossOrigin(origins="http://localhost:4200")
   public ResponseEntity<OrderItem> processCODorder(@PathVariable Long addressid) {
	   CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  List<CartItem> cartItems= cartService.getCartItemsByUserIdAndOrderId(user.getUser().getUser_id());
		  OrderItem obj=new OrderItem();
		  obj.setAmt(getCartTotal().getBody());
		  obj.setAddressid(addressid);
		  obj.setUserid(user.getUser().getUser_id());
		  obj.setMode(paymentMode.COD);
		  obj.setStatus(Status.PLACED);
		  orderService.save(obj);
		  
		  
		  for(int i=0;i<cartItems.size();i++) {
			  cartItems.get(i).setOrderid(obj.getId());
			  cartService.save(cartItems.get(i));
		  }
		  
		  return ResponseEntity.ok().body(obj);
		  
	   }
   
   
   @GetMapping("/getOrders")
   @CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<List<OrderItem>> getOrders(){
		CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<OrderItem> orderlist=orderService.getOrderByUserId(user.getUser().getUser_id());
		return ResponseEntity.ok().body(orderlist);
		
	}
   
  
   
   
	   
   }

	
   
   
   
   
	
   
   
