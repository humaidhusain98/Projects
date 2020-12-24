from django.db import models

# Create your models here.
class OrderItem(models.Model):
	addressId	=	models.IntegerField()
	userId		=	models.IntegerField()
	amt			=	models.DecimalField(max_digits=10,decimal_places=2)
	paymentMode	=	models.CharField(max_length=100)
	status		=	models.CharField(max_length=100)

	def __str__(self):
		return str(self.amt)