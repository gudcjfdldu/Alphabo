from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User

class UserProfile(models.Model):
    user = models.OneToOneField(User)
    email = models.CharField(max_length=200) 
    install_time = models.IntegerField(default=0)
    exit_time = models.IntegerField(default=0)
    
    def __unicode__(self):
        return self.title

    def update(self, install_time, exit_time):
        self.install_time = install_time
        self.exit_time = exit_time
        


# Create your models here.
