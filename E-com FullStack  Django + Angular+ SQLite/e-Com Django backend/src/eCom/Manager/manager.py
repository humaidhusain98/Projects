from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
from CartItem.models import CartItem
from CartItem.serializers import CartItemSerializer
from product.models import Product
from product.serializers import ProductSerializer
from OrderItem.models import OrderItem
from OrderItem.serializers import OrderItemSerializer
#never us loops to find objects use filter function instead which is much faster
@api_view(['GET'])
def getCart(request):
	if request.user.is_authenticated:
		userid=request.user.id
		allobject=CartItem.objects.filter(userId=userid,orderId=-999)
		serializer = CartItemSerializer(allobject ,many=True)
		return Response(serializer.data,status=status.HTTP_200_OK)
	return Response(status=status.HTTP_403_FORBIDDEN)


@api_view(['GET'])
def addProductToCart(request,productId):
	if request.user.is_authenticated:
		try:
			product = Product.objects.get(pk=productId)
		except Product.DoesNotExist:
			return Response(status=status.HTTP_404_NOT_FOUND)
		
		cartitem = CartItem.objects.filter(productId=productId,userId=request.user.id).first()
		if cartitem:
			cartitem.qty=cartitem.qty+1
			cartitem.save()
			serializer=CartItemSerializer(cartitem)
			return Response(serializer.data,status=status.HTTP_202_ACCEPTED)
		else:
			newcartobj = CartItem(productId=productId,
			productName=product.name,userId=request.user.id,
			price=product.price,orderId=-999,qty=1
			)
			newcartobj.save()
			serializer = CartItemSerializer(newcartobj)
			return Response(serializer.data,status=status.HTTP_200_OK)
	return Response(status=status.HTTP_403_FORBIDDEN)


@api_view(['GET'])
def removeProductFromCart(request,productId):
	if request.user.is_authenticated:
		try:
			product = Product.objects.get(pk=productId)
		except Product.DoesNotExist:
			return Response(status=status.HTTP_404_NOT_FOUND)

		cartitem 	=	CartItem.objects.filter(productId=productId,userId=request.user.id).first()
		if cartitem:
			serializer = CartItemSerializer(cartitem)
			if cartitem.qty == 1:
				cartitem.delete()
			else:
				cartitem.qty = cartitem.qty -1
				cartitem.save()
			return Response(serializer.data,status=status.HTTP_202_ACCEPTED)
		return Response(serializer.errors,status=status.HTTP_404_NOT_FOUND)


	return Response(status=status.HTTP_403_FORBIDDEN)



@api_view(['GET'])
def getTotal(request):
	if request.user.is_authenticated:
		total=0
		cartitem=CartItem.objects.filter(userId=request.user.id,orderId=-999)
		if cartitem:
			for item in cartitem:
				total = total+item.price*item.qty;
		resp={"total":total}
		return Response(resp,status=status.HTTP_202_ACCEPTED)

	return Response(status=status.HTTP_403_FORBIDDEN)



@api_view(['GET'])
def getCartItemById(request,id):
	if request.user.is_superuser:
		cartitem=CartItem.objects.get(pk=id)
		if cartitem:
			serializer=CartItemSerializer(cartitem)
			return Response(serializer.data,status=status.HTTP_200_OK)
		return Response(status=status.HTTP_404_NOT_FOUND)
	return Response(status=status.HTTP_403_FORBIDDEN)

@api_view(['GET'])
def getAllCartItem(request):
	if request.user.is_superuser:
		cartitem=CartItem.objects.all()
		if cartitem:
			serializer=CartItemSerializer(cartitem,many=True)
			return Response(serializer.data,status=status.HTTP_200_OK)
		return Response(status=status.HTTP_404_NOT_FOUND)
	return Response(status=status.HTTP_403_FORBIDDEN)


@api_view(['GET'])
def processCodOrder(request,addressId):
	if request.user.is_authenticated:
		total=0
		usercartitems = CartItem.objects.filter(userId=request.user.id,orderId=-999)
		if usercartitems:
			for item in usercartitems:
				total=total+item.qty*item.price
			neworderobj=OrderItem(userId=request.user.id,addressId=addressId,
				amt=total,paymentMode="COD",status="placed"
			)
			neworderobj.save()
			for item in usercartitems:
				item.orderId=neworderobj.id
				item.save()
			serializer = OrderItemSerializer(neworderobj)
			return Response(serializer.data,status.HTTP_200_OK)
		return Response(status=status.HTTP_404_NOT_FOUND)


	return Response(status=status.HTTP_403_FORBIDDEN)



@api_view(['GET'])
def getUserOrders(request):
	if request.user.is_authenticated:
		orders = OrderItem.objects.filter(userId=request.user.id)
		if orders:
			serializer=OrderItemSerializer(orders,many=True)
			return Response(serializer.data,status=status.HTTP_200_OK)

		return Response(status=status.HTTP_404_NOT_FOUND)

	return Response(status=status.HTTP_403_FORBIDDEN)











		# allobj = CartItem.objects.all()
		# saveobj = null

		# newcartobj = CartItem(productId=productId,
		# 	productName=product.name,userId=request.user.id,
		# 	price=product.price,orderId=-999,qty=1
		# 	)
		# newcartobj.save()
		# serializer = CartItemSerializer(newcartobj)
		# return Response(serializer.data,status=status.HTTP_200_OK)
	
	



# @api_view(['GET'])
# def removeProductFromCart(request,productId):
# 	if request.user.is_authenticated:
# 		allobj = CartItem.objects.filter(userId=)
# 		saveobj = null
# 		for obj in allobj:
# 			if (obj.productId == productId) && (obj.userId == request.user.id)
# 				saveobj = obj
# 				break
# 		if(saveobj == null):
# 			return Response(status=status.HTTP_404_NOT_FOUND)
# 		elif (saveobj.qty == 1):
# 			saveobj.delete()
# 			return Response(status=status.HTTP_204_NO_CONTENT)
# 		else:
# 			saveobj.qty=saveobj.qty-1
# 			saveobj.save()
# 			return Response(status = status.HTTP_202_ACCEPTED)
# 	return Response(status=status.HTTP_403_FORBIDDEN)
				










