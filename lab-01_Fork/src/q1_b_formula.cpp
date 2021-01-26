// Q1.b

#include <iostream>
#include <vector>
#include <unistd.h>
#include <stdio.h>
#include<sys/wait.h> 
#include <math.h>

using namespace std;

vector<int> fact;

int main(int argc, char const *argv[])
{

	int ini = 0;
	int end = 0;

	cin >> ini >> end;

	int pidA = fork();

	if ((ini <= end) && (ini > 0) && (end > 0)){
		for (int i = 0; i < end; i++) {
			fact.emplace_back(0);
		}

		int end1 = (int) end/2;
		
		for (int i = 0; i < 2; i++) {
			switch(vfork()) {
				case 0: 
					fact[0] = 1;
					fact[1] = 1;
					cout << "Child " << i + 1 << " pid = " << getpid() << endl;
					if (i == 0) { // child 1
						for (int j = 1; j <= end1; j++) { 
							fact[j-1] = (1/(sqrt(5)))*pow(((1+sqrt(5))/2), j)-((1/(sqrt(5)))*pow(((1-sqrt(5))/2), j));
						}
					} else { // child 2
						for (int k = end1+1; k <= end; k++) { 
							fact[k-1] = (1/(sqrt(5)))*pow(((1+sqrt(5))/2), k)-((1/(sqrt(5)))*pow(((1-sqrt(5))/2), k));
						}
					}
					exit(0);
				case -1: return -1;
				default: continue;
					
			}
		}

		for (int i = 0; i < 2; i++) {
			wait(NULL);	
		}

		for (int i = ini-1; i < end; i++) {
			cout << fact[i] << endl;
		}
	}

	return 0;
}