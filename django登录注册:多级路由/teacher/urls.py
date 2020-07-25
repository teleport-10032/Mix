import json

import docker
import pymysql
from django.http import HttpResponse
from django.shortcuts import render
from django.urls import path
from pymysql.cursors import DictCursor




def getIndex(request):
    return render(request,'teacher_index.html')
def getHomework(request):
    json_list = []
    db = pymysql.connect("localhost", "root", "a1021822981", "net")
    cursor = db.cursor()
    sql = "select id,title,des from exp"
    cursor.execute(sql)
    results = cursor.fetchall()
    # print(results)
    # for row in results:
    #     json_dict = {}
    #     json_dict["id"] = row[0]
    #     json_dict["title"] = row[1]
    #     json_dict["des"] = row[2]
    #     json_list.append(json_dict)
    return render(request,'teacher_homework.html',{'results': results})

def getSystem(request):
    return render(request,'teacher_system.html')

urlpatterns = [
    path('/index/',getIndex),
    path('/homework/',getHomework),
    path('/system/',getSystem)
]
