from django.db import models
# Create your models here.

class CartItem(models.Model):
	productId	=	models.IntegerField()
	productName =	models.TextField()
	userId		=	models.IntegerField()
	price		=	models.DecimalField(max_digits=10,decimal_places=2)
	orderId		=	models.IntegerField()
	qty			=	models.IntegerField()

	def __str__(self):
		return self.productName



