from django.shortcuts import render
from django.urls import path


def getIndex(request):
    return render(request,'student_index.html')
def getHomework(request):
    return render(request,'student_homework.html')
def getApply(request):
    return render(request,'student_apply.html')

urlpatterns = [
    path('/index/',getIndex),
    path('/homework/',getHomework),
    path('/apply/',getApply)
]
