from django.db import models

# Create your models here.
class Address(models.Model):
	name		= 	models.CharField(max_length=100)
	phonenumber	=	models.CharField(max_length=15)
	landmark	=	models.CharField(max_length=100)
	addrLine1	=	models.TextField()
	addrLine2	=	models.TextField()
	city		=	models.CharField(max_length=100)
	state		=	models.CharField(max_length=100)
	country		=	models.CharField(max_length=100)
	pincode		=	models.IntegerField()
	userId		=	models.IntegerField(default=-999)

	def __str__(self):
		return self.name