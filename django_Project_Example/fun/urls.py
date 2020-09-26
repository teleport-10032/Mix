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
import datetime
import pymysql
from django.contrib import admin
from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.urls import path, include

def getLogin(request):
    return render(request,"login.html")
def getRegister(request):
    return render(request,"register.html")

def register_fun(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        passwd = request.POST.get('passwd')
        repasswd = request.POST.get('repasswd')
        hash = passwd
        hash1 = hashlib.md5()
        hash1.update(hash.encode("utf-8"))
        passwd_hash = hash1.hexdigest()
        dt = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

        if passwd != repasswd:
            return render(request, 'register.html', {'script': "alert", 'msg': '两次密码输入不一致'})

        db = pymysql.connect("localhost", "root", "a1021822981", "net")
        cursor = db.cursor()
        sql = "select count(*) from user where username='%s'" % (username)
        print(sql)
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            for row in results:
                        cnt = row[0]
            if cnt != 0:
                return render(request, 'register.html', {'script': "alert", 'msg': '用户名已存在'})
            db.commit()
        except:
            db.rollback()

        sql = "insert into user(username,passwd,teacher_id,time,type) values('%s','%s','-1','%s','student')" % (username,passwd_hash,dt)
        print(sql)
        try:
            cursor.execute(sql)
            db.commit()
        except:
            print("!!")
            db.rollback()
        db.close()
        return render(request, 'login.html', {'script': "alert", 'msg': '注册成功'})


def login_fun(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        passwd = request.POST.get('passwd')
        hash = passwd
        hash1 = hashlib.md5()
        hash1.update(hash.encode("utf-8"))
        passwd_hash = hash1.hexdigest()
        db = pymysql.connect("localhost", "root", "a1021822981", "net")
        cursor = db.cursor()
        sql = "select id,passwd,type from user where username='%s' limit 1" % (username)
        print(sql)
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            for row in results:
                id_re = row[0]
                passwd_re = row[1]
                type_re = row[1]
            if passwd_re == passwd_hash:
                request.session['username'] = username
                request.session['id'] = id_re
                request.session['type'] = type_re
                return render(request, 'student_index.html', {'script': "alert", 'msg': '登录成功'})
            db.commit()
        except:
            db.rollback()
        db.close()
        return render(request, 'login.html', {'script': "alert", 'msg': '用户名或密码不正确'})


def login_out(request):
    request.session.flush()
    return redirect('/')

urlpatterns = [
    path('/login/', getLogin),
    path('/register/', getRegister),
    path('/login_fun/', login_fun),
    path('/register_fun/',register_fun),
    path('/login_out/',login_out)
]
