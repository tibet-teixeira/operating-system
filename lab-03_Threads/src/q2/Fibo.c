#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* fibo_result; /* this data is shared by the thread(s) */
void *fib(void *param); /* the thread */

int main(int argc, char *argv[]) {
	pthread_t tid; /* the thread identifier */
	pthread_attr_t attr; /* set of attributes for the thread */

	if (argc != 2) {
		fprintf(stderr,"usage: a.out <integer value>\n");
		return -1;
	}

	if (atoi(argv[1]) <= 0) {
		fprintf(stderr,"Argument %d must be positive\n", atoi(argv[1]));
		return -1;
	}

	/* get the default attributes */
	pthread_attr_init(&attr);

	/* create the thread */
	pthread_create(&tid, &attr, fib, argv[1]);

	/* now wait for the thread to exit */
	pthread_join(tid, NULL);

	printf("Fibonacci with %d elements = %s\n", atoi(argv[1]), fibo_result);
	free(fibo_result);
}


/**
* The thread will begin control in this function
*/
void *fib(void *param) {
	// param = 5 => 1 1 2 3 5 = 12
	int i, upper = atoi(param);

	fibo_result = (char*) malloc(((upper * 2) - 1) * sizeof(char));

	if (upper == 1) strcat(fibo_result, "1");

	if (upper == 2) strcat(fibo_result, "1 1");

	if (upper > 2) {
		int current_result;
		char* result_str = "";
		int* fibo_vector = (int*) malloc(upper * sizeof(int));
		fibo_vector[0] = 1;
		fibo_vector[1] = 1;

		strcat(fibo_result, "1 1");

		int length;
		char* str;
		
		for (i = 2; i < upper; i++) {
			current_result = fibo_vector[i - 2] + fibo_vector[i - 1];
			fibo_vector[i] = current_result;

			length = snprintf(NULL, 0, "%d", current_result);
			str = malloc(length + 1);
			snprintf(str, length + 1, "%d", current_result);

			strcat(fibo_result, " ");
			strcat(fibo_result, str);
		}

		free(str);
		free(fibo_vector);
	}
	
	pthread_exit(0);
}