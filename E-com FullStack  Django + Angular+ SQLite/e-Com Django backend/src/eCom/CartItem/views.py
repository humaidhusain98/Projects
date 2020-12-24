from django.shortcuts import render
from .serializers import CartItemSerializer
from .models import CartItem
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
# Create your views here.

@api_view(['GET', 'POST'])
def getAllCartItem(request):
	if request.method == 'GET':
		cartitem 	= CartItem.objects.all()
		serializer 	= CartItemSerializer(cartitem, many=True)
		return Response(serializer.data)
	
	if request.method == 'POST':
		serializer = CartItemSerializer(data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data,status=status.HTTP_201_CREATED)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET', 'PUT','DELETE'])
def cartItem_detail(request,pk):
	try:
		cartitem = CartItem.objects.get(pk=pk)
	except CartItem.DoesNotExist:
		return Response(status=status.HTTP_404_NOT_FOUND)

	if request.method == 'GET':
		serializer = CartItemSerializer(cartitem)
		return Response(serializer.data,status=status.HTTP_201_CREATED)

	if request.method == 'PUT':
		serializer=CartItemSerializer(cartitem,data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)

	if request.method == 'DELETE':
		cartitem.delete()
		return Response(status=status.HTTP_204_NO_CONTENT)




