from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User

class UserProfile(models.Model):
    device_id = models.CharField(max_length=200)
    user = models.OneToOneField(User)
    nickname = models.CharField(max_length=200)
    win = models.IntegerField(blank=False, default=0)
    lose = models.IntegerField(blank=False, default=0)
    draw = models.IntegerField(blank=False, default=0)

class Time(models.Model):
    install_time = models.DateTimeField('start time')
    play_time = models.DateTimeField('play time')
    exit_time = models.DateTimeField('exit time')
    last_time = models.DateTimeField('last time')

# Create your models here.
