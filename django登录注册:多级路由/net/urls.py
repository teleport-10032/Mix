"""net URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/dev/topics/http/urls/
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
import hashlib
import sys
from imp import reload

from django.contrib import admin
from django.http import HttpResponse
from django.urls import path, include
from fun.urls import getLogin



def test(request):
    hash = "hello"
    hash1 = hashlib.md5()
    hash1.update(hash.encode("utf-8"))
    print(hash1.hexdigest())
    return HttpResponse('success')

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', getLogin),
    path('student',include("student.urls")),
    path('teacher',include("teacher.urls")),
    path('fun',include("fun.urls")),
    path('test/',test)
]
