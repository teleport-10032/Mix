
#include<iostream>
#include<cstdio>
using namespace std;
/*
您的暴力程序或标程。只需要加上头文件 #include <cstdio>

同样在main()函数的前两行加上
    freopen("input.in","r",stdin);
    freopen("output1.out","w",stdout);
即可。
*/

bool cmp(string a,string b)
{
    int i,j;
    for(i=0,j=0; a[i]!='\0'&&b[i]!='\0'; ++i,++j)
        if(a[i]!=b[i])
            return false;
    return true;
}
int main()
{
    
        freopen("input.in","r",stdin);
    freopen("output2.out","w",stdout);


    int n,flag,i,j;
    string a[1000];
    while(~scanf("%d",&n)&&n != 0)
    {

        flag=1;
        for(i=0; i<n; ++i)
            cin>>a[i];
        for(i=0; i<n&&flag; ++i)
            for(j=i+1; j<n&&flag; ++j)
            {
                if(cmp(a[i],a[j]))
                {
                    flag=0;
                    printf("NO\n");
                    break;
                }
            }
        if(flag)
            printf("YES\n");
    }
    return 0;
}