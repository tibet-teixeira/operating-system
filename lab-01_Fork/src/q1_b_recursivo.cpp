// Q1.b (recursivo)


#include <iostream>
#include <vector>
#include <unistd.h>
#include <stdio.h>
#include<sys/wait.h> 

using namespace std;
int factorial(int n);

vector<int> fact;

int main(int argc, char const *argv[])
{

	int ini = 0;
	int end = 0;

	cin >> ini >> end;

	if ((ini <= end) && (ini > 0) && (end > 0)){
		for (int i = 0; i < end; i++) {
			fact.emplace_back(0);
		}

		for (int i = 0; i < 2; i++) {
			switch(vfork()) {
				case 0: 
					fact[0] = 1;
					fact[1] = 1;
					factorial(end - (i + 1));
					exit(0);
				case -1: return -1;
				default: continue;
					
			}
		}

		for (int i = 0; i < 2; i++) {
			wait(NULL);	
		}

		fact[end-1] = fact[end-2] + fact[end-3];

		for (int i = ini-1; i < end; i++) {
			cout << fact[i] << endl;
		}
	}

	return 0;
}

int factorial(int n) {
	if (n == 1) {
		return 1;
	}
	if (n == 2) {
		return 1;
	}

	if (fact[n-2] == 0) {
		fact[n-2] = factorial(n-1);	
	}

	if (fact[n-3] == 0) {
		fact[n-3] = factorial(n-2);
	}

	fact[n-1] = fact[n-2] + fact[n-3];

	return fact[n-1];
}