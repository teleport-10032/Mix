"""Test URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from django.urls import path, include

from django.shortcuts import HttpResponse,render,redirect

def index(request):
    # 业务逻辑
    # 返回结果
    #分别是返回字符串和页面
    #return HttpResponse('test2')
    return render(request,'index.html')

def login(request):
    print("success")
    if request.method == 'POST':
        username = request.POST.get('username')
        passwd = request.POST.get('passwd')
    print(username)
    print(passwd)
    # 重定向
    return redirect('/success/')
def view(request):
    return HttpResponse('重定向成功')

urlpatterns = [
    path('admin/', admin.site.urls),
    # localhost:8000/index或locahost:8000则调用index函数
    path('index/',index),
    path('',index),
    path('login/',login),
    path('success/',view),

]
