from django.shortcuts import render
from .serializers import OrderItemSerializer
from .models import OrderItem
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
# Create your views here.

@api_view(['GET', 'POST'])
def getAllOrderItem(request):
	if request.method == 'GET':
		orderitem 	= OrderItem.objects.all()
		serializer 	= OrderItemSerializer(orderitem, many=True)
		return Response(serializer.data)
	
	if request.method == 'POST':
		serializer = OrderItemSerializer(data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data,status=status.HTTP_201_CREATED)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET', 'PUT','DELETE'])
def orderItem_detail(request,pk):
	try:
		orderitem = OrderItem.objects.get(pk=pk)
	except OrderItem.DoesNotExist:
		return Response(status=status.HTTP_404_NOT_FOUND)

	if request.method == 'GET':
		serializer = OrderItemSerializer(orderitem)
		return Response(serializer.data,status=status.HTTP_201_CREATED)

	if request.method == 'PUT':
		serializer=OrderItemSerializer(orderitem,data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)

	if request.method == 'DELETE':
		orderitem.delete()
		return Response(status=status.HTTP_204_NO_CONTENT)




