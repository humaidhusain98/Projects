from django.shortcuts import render
from .models import Product
from .serializers import ProductSerializer
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view

# class ProductAPI(APIView):
# 	def get(self,request):
# 		products = Product.objects.all()

#  		serializer = ProductSerializer(products)
#  		return Response(serializer.data)

#  	def post(self,request):
#  		serializer = ProductSerializer(data=request.data)
#  		if serializer.is_valid():
#  			serializer.save()
#  			return Response(serializer.data,status=status.HTTP_201_CREATED)
# 		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST) 		






#Create your views here.
@api_view(['GET', 'POST'])
def getAllProduct(request):
	if request.method == 'GET':
		products 	= Product.objects.all()
		serializer 	= ProductSerializer(products, many=True)
		return Response(serializer.data)
	
	if request.method == 'POST':
		serializer = ProductSerializer(data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data,status=status.HTTP_201_CREATED)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET', 'PUT','DELETE'])
def product_detail(request,pk):
	try:
		product = Product.objects.get(pk=pk)
	except Product.DoesNotExist:
		return Response(status=status.HTTP_404_NOT_FOUND)

	if request.method == 'GET':
		serializer = ProductSerializer(product)
		return Response(serializer.data,status=status.HTTP_201_CREATED)

	if request.method == 'PUT':
		serializer=ProductSerializer(product,data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)

	if request.method == 'DELETE':
		product.delete()
		return Response(status=status.HTTP_204_NO_CONTENT)




