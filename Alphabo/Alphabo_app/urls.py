from django.conf.urls import url, include

from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^login/$', views.login_handler, name='login'),
    url(r'^logout/$', views.logout_handler, name='logout'),
    url(r'^login/facebook/', views.login_facebook, name='facebook'),
    url(r'^user/register/', views.register_handler, name='register'),
    url(r'^game/$', views.game_handler, name='game'),
    url(r'^contact/$', views.contact_handler, name='contact'),
    url(r'^time/$', views.time_handler, name='time'),
    url(r'^private/$', views.private_handler, name='private'),
]
