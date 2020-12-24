from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
from Address.models import Address
from Address.serializers import AddressSerializer

@api_view(['GET'])
def getUserAddress(request):
	if request.user.is_authenticated:
		addr=Address.objects.filter(userId=request.user.id)
		if addr:
			serializer=AddressSerializer(addr,many=True)
			return Response(serializer.data,status=status.HTTP_200_OK)
		return Response(status=status.HTTP_404_NOT_FOUND)
	return Response(status=status.HTTP_403_FORBIDDEN)

@api_view(['POST'])
def postUserAddress(request):
	if request.user.is_authenticated:
		serializer = AddressSerializer(data=request.data)

		if serializer.is_valid():
			serializer.validated_data['userId']=request.user.id
			serializer.save()
			return Response(serializer.data,status=status.HTTP_201_CREATED)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)


	return Response(status=status.HTTP_403_FORBIDDEN)

@api_view(['POST'])
def deleteUserAddress(request):
	if request.user.is_authenticated:
		try:
			address = Address.objects.get(pk=request.data['id'])
		except Address.DoesNotExist:
			return Response(status=status.HTTP_404_NOT_FOUND)
		address.userId=-9999
		address.save()
		serializer=AddressSerializer(address)
		return Response(serializer.data,status=status.HTTP_200_OK)
	return Response(status=status.HTTP_403_FORBIDDEN)

