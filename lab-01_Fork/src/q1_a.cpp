// Q1.a

#include <iostream>
#include <vector>

using namespace std;

int main(int argc, char const *argv[]) {

	int ini = 0;
	int end = 0;

	cin >> ini >> end;

	if ((ini <= end) && (ini > 0) && (end > 0)){
		vector<int> fact;

		for (int i = 0; i < end; i++) {
			fact.emplace_back(0);
		}

		fact[0] = 1;
		fact[1] = 1;

		for (int i = 2; i < end; i++) {
			fact[i] = fact[i-1] + fact[i-2];
		}
		

		for (int i = ini - 1; i < end; i++) {
			cout << fact[i] << endl;
		}
	}
	return 0;
}