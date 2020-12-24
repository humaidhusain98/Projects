"""eCom URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path,include
from product.views import getAllProduct,product_detail
from Address.views import getAllAddress,address_detail
from CartItem.views import getAllCartItem,cartItem_detail 
from OrderItem.views import getAllOrderItem,orderItem_detail 
from Manager.manager import getCart,addProductToCart,removeProductFromCart,getTotal,getCartItemById
from Manager.manager import getAllCartItem,processCodOrder,getUserOrders
from Manager.addressManager import getUserAddress,postUserAddress,deleteUserAddress


urlpatterns = [
    #product
	path('',getAllProduct),
	path('<int:pk>/',product_detail),
    #address
    path('address/',getAllAddress),
    path('address/<int:pk>/',address_detail),

    #CartItem
    path('cartItem/',getAllCartItem),
    path('cartItem/<int:pk>/',cartItem_detail),

    #OrderItem
    path('orderItem/',getAllOrderItem),
    path('orderItem/<int:pk>/',orderItem_detail),

    #admin
    path('admin/', admin.site.urls),

    #ManagerUserControls
    path('user/cart/',getCart),
    path('user/cart/add/<int:productId>',addProductToCart),
    path('user/cart/remove/<int:productId>',removeProductFromCart),
    path('user/cart/total',getTotal),
    path('user/address',getUserAddress),
    path('user/add/address',postUserAddress),
    path('user/deleteaddress',deleteUserAddress),
    path('user/address/<int:addressId>',processCodOrder),
    path('user/getAllOrders',getUserOrders),

    #ManagerAdminControls
    path('admin/cart/<int:id>',getCartItemById),
    path('admin/cart',getAllCartItem),

    #accounts
    path('',include("accounts.urls")),

    #passwordReset
    path('api/password_reset/', include('django_rest_passwordreset.urls', namespace='password_reset')),

    #emailer
    path('',include("emailer.urls")),
]
