#include "iostream"
#include "cstdio"
#include "cstdlib"
using namespace std;

int main()
{
    int n ; 
    cout << "输入对拍次数" << endl;
    cin >> n;
    for(int i = 1 ; i <= n ; i ++)
    {
            system("./make");
            system("./program1");
            system("./program2");

            cout << "第" << i << "次对拍:" << endl;

            if (system("diff output1.out output2.out"))
            {
                cout << "WA" << endl;
                
                cout << "input" << endl;
                system("cat input.in");
                cout << endl;

                cout << "output1" << endl;
                system("cat output1.out");
                cout << endl;
                
                cout << "output2" << endl;
                system("cat output2.out");
                cout << endl;
                
                return 0;
            }
            else 
                cout << "AC" << endl;
    }
    return 0;
}
