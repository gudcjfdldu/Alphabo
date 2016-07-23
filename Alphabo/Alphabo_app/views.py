import urllib

from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, HttpResponseRedirect
from django.core.urlresolvers import reverse
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout

from Alphabo_app.models import UserProfile

# Create your views here.

error_dict = {
    'register-required': 'you have to register account',
    'match-password': 'please match both password',
    'same-username': 'already have been same username',
    'account-disabled': 'The password is valid, but the account the has been disabled!',
    'input-mustbeset': 'The given username and password must be set',
    'input-incorrect': 'The username and password incorrect',
}

def login_required(error_code=None):
    def decorator(func):
        def inner(request, *args, **kwargs):
            if request.user.is_authenticated():
                return func(request, *args, **kwargs)
            else:
                url = reverse('auth.login')
                if error_code is not None:
                    parameters = {
                        'error_code': error_code
                    }

                    url += '?' + urllib.urlencode(parameters)
                return HttpResponseRedirect(url)
        return inner
    return decorator


def index(request):
    if request.method == 'GET':
	if request.user.is_authenticated():
            current_user = request.user
            context = {'current_user': current_user }
            return render(request, 'Alphabo_app/index.html', context)
	return render(request, 'Alphabo_app/index.html')


def login_handler(request):
    if request.method == 'GET':
        if request.user.is_authenticated():
          return HttpResponseRedirect(reverse('index'))
        return render(request, 'Alphabo_app/login.html')

    elif request.method == 'POST':
       username = request.POST.get('username', '')
       password = request.POST.get('password', '')
       user = authenticate(username=username, password=password)
       if user is not None:
           if user.is_active:
               login(request, user)
               return HttpResponseRedirect(reverse('index'))
           error_code = 'account-disabled'
       else:
           error_code = 'input-incorrect'
       context = {'error_message': error_dict[error_code]}
       return render(request, 'Alphabo_app/login.html', context)
    if request.GET.get('error_code', ''):
        error_code = request.GET.get('error_code')
    else:
        return render(request, 'Alphabo_app/login.html')
    context = {'error_message': error_dict[error_code]}
    return render(request, 'Alphabo_app/login.html', context) 


def login_facebook(request):
    if request.method == 'GET':
        return render(request, 'Alphabo_app/facebook-redirect.html')


def logout_handler(request):
    if request.method == 'GET':
        logout(request)
        return HttpResponseRedirect(reverse('login'))


def game_handler(request):
    if request.method == 'GET':
        if request.user.is_authenticated():
            current_user = request.user
            context = {'current_user': current_user }
            return render(request, 'Alphabo_app/game.html', context);
        return render(request, 'Alphabo_app/game.html')



def register_handler(request):
    if request.method == 'GET':
        return render(request, 'Alphabo_app/register.html')
    elif request.method == 'POST':
        user_object_list = User.objects.all()
        username = request.POST.get('username', '')
        if username == '':
            context = {'error_message': error_dict['input-mustbeset']}
            return render(request, 'noticeboard/register.html', context)
        for user in user_object_list:
            if user.username == username:
                context = {'error_message': error_dict['same-username']}
                return render(request, 'Alphabo_app/register.html', context)
        email = request.POST.get('email', '')
        password = request.POST.get('password', '')
        confirm_password = request.POST.get('confirm-password', '')
        if password != confirm_password:
            context = {'error_message': error_dict['match-password']}
            return render(request, 'Alphabo_app/register.html', context)
        else:
            user = User.objects.create_user(username, email, password)
            UserProfile.objects.create(user=user, email=user.email)
            return HttpResponseRedirect(reverse('index'))

       
def time_handler(request):
    if request.method == 'POST':
        if request.user.is_authenticated():
            current_user = request.user
            profile = get_object_or_404(UserProfile, user=current_user)
            install_time = request.POST.get('installtime', '')
            exit_time = request.POST.get('exittime', '')
            profile.update(install_time, exit_time)
            return HttpResponseRedirect(reverse('index'))

    elif request.method == 'GET':
        print 'get request'
        return HttpResponseRedirect(reverse('login'))



def contact_handler(request):
    if request.method == 'GET':
        if request.user.is_authenticated():
            current_user = request.user
            context = {'current_user': current_user }
            return render(request, 'Alphabo_app/profile.html', context)
        return render(request, 'Alphabo_app/profile.html')



def private_handler(request):
    if request.method == 'GET':
        if request.user.is_authenticated():
            current_user = request.user
            context = {'current_user': current_user }
            return render(request, 'Alphabo_app/private.html', context)
        return render(request, 'Alphabo_app/private.html')



