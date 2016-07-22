from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User

class UserProfile(models.Model):
    user = models.OneToOneField(User)
    email = models.CharField(max_length=200) 
    install_time = models.IntegerField(default=0)
    exit_time = models.IntegerField(default=0)
    play_time = models.IntegerField(default=0)
    last_time = models.IntegerField(default=0)



# Create your models here.
