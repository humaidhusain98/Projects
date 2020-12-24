from django.views.generic import TemplateView
from django.urls import path
from emailer import views
# Create your views here.

urlpatterns = [
    path('emailer/home/', TemplateView.as_view(template_name="home.html"), name='home'),
    path('emailer/home/send-form-email',views.SendFormEmail.as_view(),name='send_email')
] 