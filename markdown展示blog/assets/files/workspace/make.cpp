#include "iostream"
#include<cstdio>
#include<cstdlib>
#include<ctime>
using namespace std;

/*
演示题目地址:https://acm.sdut.edu.cn/onlinejudge3/problems/2151

本题输入为一个n，下边的n行每行包含一个号码。
程序通过cout输出这些信息存储至input.in。
rand()是以时间为种的随机数，rand()%100表示该随机数在100以内取值

*/
int main()
{
    freopen("input.in" ,"w" ,stdout);
    srand(time(NULL));
    
    int n = rand() % 1000;
    cout << n << endl;
    
    for(int i = 1 ; i <= n ; i ++)
    {	
	    int x = rand()%10000 ;
	    cout << x <<endl;
    }

    return 0;
}
