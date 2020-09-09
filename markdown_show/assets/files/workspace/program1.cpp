#include "cstdio"
#include "iostream"
#include "stdio.h"
#include "string.h"
#include "stdlib.h"
#include "time.h"
#include <cstdio>
using namespace std;
/*
您需要对拍的程序。只需要加上头文件 #include <cstdio>

在main()函数的前两行加上
    freopen("input.in","r",stdin);
    freopen("output1.out","w",stdout);
即可。
*/
int main()
{
    freopen("input.in","r",stdin);
    freopen("output1.out","w",stdout);

    string str[1005];
    int n;
    while(~scanf("%d",&n))
    {
        if(n == 0)
            return 0;
        for(int i = 1 ; i <= n ; i ++)
            cin >> str[i];
        int flag = 0;
        for(int i = 1 ; i <= n ; i ++)
        {
            int len1 = str[i].length();
            for(int j = 1 ; j <= n ; j ++)
            {
                if(i == j)
                    continue;

                int len2 = str[j].length();
                if(str[i] == str[j])
                    flag = 1;
                else if(len2 < len1)
                {
                    int f = 0;
                    for(int k = 0 ; k < len2 ; k ++)
                        if(str[i][k] != str[j][k])
                            f = 1;
                    //f == 0说明str[j]是str[i]的前缀
                    if(f == 0)
                        flag = 1;
                }
            }
        }
        if(flag == 1)
            cout << "NO" << endl;
        else
            cout << "YES" << endl;
    }
    return 0;
}
