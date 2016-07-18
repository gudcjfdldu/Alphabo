from django.conf.urls import url, include

from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^login/$', views.login_handler, name='login'),
    url(r'^login/facebook/', views.login_facebook, name='facebook'),
    url(r'^game/$', views.game_handler, name='game'),
]
