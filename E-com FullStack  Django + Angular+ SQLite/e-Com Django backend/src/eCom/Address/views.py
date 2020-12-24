from django.shortcuts import render
from .models import Address
from .serializers import AddressSerializer
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view

@api_view(['GET','POST'])
def getAllAddress(request):
	if request.method == 'GET':
		address 	= Address.objects.all()
		serializer 	= AddressSerializer(address, many=True)
		return Response(serializer.data)
	
	if request.method == 'POST':
		serializer = AddressSerializer(data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data,status=status.HTTP_201_CREATED)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)




@api_view(['GET', 'PUT','DELETE'])
def address_detail(request,pk):
	try:
		address = Address.objects.get(pk=pk)
	except Address.DoesNotExist:
		return Response(status=status.HTTP_404_NOT_FOUND)

	if request.method == 'GET':
		serializer = AddressSerializer(address)
		return Response(serializer.data,status=status.HTTP_201_CREATED)

	if request.method == 'PUT':
		serializer=AddressSerializer(address,data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)

	if request.method == 'DELETE':
		address.delete()
		return Response(status=status.HTTP_204_NO_CONTENT)







# Create your views here.
