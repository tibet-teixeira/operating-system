import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Inspector {
	int[] available;
	int[] amountResource;
	int[][] max;
	int[][] allocation;
	int[][] need;
	int[][] request;
	ArrayList<String> processes;
	int m = 0;
	int n = 0;

	Inspector() {}

	public void setAmountResource(){
		this.amountResource = new int[]{20, 10, 10};
	}

	public void printVector(int[] vector) {
		String data = " ";
		for(int i = 0; i < vector.length; ++i) {
			data = data + vector[i] + " ";
		}
		System.out.println(data);
	}

	public void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; ++i) {
			String data = " ";
			for(int j = 0; j < matrix[0].length; ++j) {
				data = data + matrix[i][j] + " ";
			}
			System.out.println(data);
		}
	}

	public void needCalculate() {
		for(int i = 0; i < this.n; ++i) {
			for(int j = 0; j < this.m; ++j) {
				this.need[i][j] = this.max[i][j] - this.allocation[i][j];
			}
		}
	}

	public void availableCalculate() {
		for(int i = 0; i < this.m; ++i) {
			int data = 0;
			for(int j = 0; j < this.n; ++j) {
				data += this.allocation[j][i];
			}
			this.available[i] = this.amountResource[i] - data;
		}
	}

	public void readFile(String filename) throws Exception {
		this.processes = new ArrayList();
		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(new FileInputStream(filename)));

		String data = null;

		while((data = bufferedReader.readLine()) != null) {
			this.processes.add(data);
		}

		setAmountResource();
		
		this.n = this.processes.size();
		String[] dataProcess = ((String)this.processes.get(0)).split(",");
		this.m = (dataProcess.length - 1) / 2;
		
		this.max = new int[this.n][this.m];
		this.available = new int[this.m];
		this.allocation = new int[this.n][this.m];
		this.need = new int[this.n][this.m];

		for(int i = 0; i < this.n; ++i) {
			dataProcess = ((String)this.processes.get(i)).split(",");
			for(int j = 0; j < this.m; ++j) {
				this.allocation[i][j] = Integer.parseInt(dataProcess[j + 1]);
				this.max[i][j] = Integer.parseInt(dataProcess[j + 4]);
			}
		}
		
		this.needCalculate();
		this.availableCalculate();
		bufferedReader.close();
	}

	public int[] copy(int[] vector) {
		int[] data = new int[vector.length];
		for(int i = 0; i < vector.length; ++i) {
			data[i] = vector[i];
		}

		return data;
	}


	public void setFalse(boolean[] vector) {
		for(int i = 0; i < vector.length; i++) {
			vector[i] = false;
		}
	}

	public boolean safety() {
		int[] work = this.copy(this.available);
		boolean[] finish = new boolean[this.n];
		this.setFalse(finish);
		int safeSeq[] = new int[this.n];
		int cont = 0;

		while(cont < this.n){
			boolean flag = false;
			for(int i = 0; i < this.n; i++) {
				if (finish[i] == false) {
					int j;
					for(j = 0; j < this.m; j++){
						if(this.need[i][j] > work[j]){
							break;
						}
					}
					if(j == this.m){
						for(int k = 0; k < this.m; k++){
							work[k] += allocation[i][k];
						}
						finish[i] = true;
						safeSeq[cont] = i;
						cont +=1;
						flag = true;
					} else {
						flag = false;
						break;
					}

				}
			}
			if(flag == false){
				System.out.println("Estado inseguro!");
				return false;
			}
		}
		System.out.println("Processos sem deadlock!\nSequencia de processos:");
		printVector(safeSeq);
		return true;
	}

	public boolean avoid(int[] request) {
		int i;
		int Pi = request[0];

		for(i = 0; i < this.m; i++) {
			if (request[i+1] > this.need[Pi][i]) {
				System.out.println("Erro, processo excedeu limite maximo de requisicoes");
				return false;
			}
		}
		
		if(i == this.m){
			for(i = 0; i < this.m; i++){
				if(request[i+1] > this.available[i]){
					System.out.println("Processo espera por recursos");
					return false;
				}
			}
			
			if(i == this.m){
				for(i = 0; i < this.m; i++){
					this.available[i] -= request[i+1];
					this.allocation[Pi][i] += request[i+1];
					this.need[Pi][i] -= request[i+1];
				}
			System.out.println("Recursos alocados!");
			return true;
			}
		}
		System.out.println("Erro");
		return false;

	}

	public boolean detection(String filename) throws Exception {
		this.request = new int[this.n][this.m];

		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(new FileInputStream(filename)));

		String request = null;

		processes.clear();
		while((request = bufferedReader.readLine()) != null) {
			this.processes.add(request);
		}

		String[] dataRequest;

		for(int i = 0; i < this.n; ++i) {
			dataRequest = ((String)this.processes.get(i)).split(",");
			for(int j = 0; j < this.m; ++j) {
				this.request[i][j] = Integer.parseInt(dataRequest[j + 1]);
			}
		}

		int[] work = this.copy(this.available);
		boolean[] finish = new boolean[this.n];

		setFalse(finish);

		int[] safeSequence =  new int[this.n];
		int numProcesses = 0;

		while (numProcesses < this.n){
			boolean found = false;
			for(int p = 0; p < this.n; p++){
				if(finish[p] == false){
					int j;
					for(j = 0; j < this.m; j++){
						if(this.request[p][j] > work[j]){
							break;
						}
					}

					if(j == this.m){
						for(int k = 0; k < this.m; k++){
							work[k] += this.allocation[p][k];
						}
						safeSequence[numProcesses] = p;
						numProcesses++;
						finish[p] = true;
						found = true;
						p = 0;
					}
				}
			}
			if(found == false) {
				System.out.println("Sistema esta em deadlock");
				return false;
			}

		}


		System.out.println("Sistema nao esta em deadlock \nSequencia segura de execucao:");
		String execSequence = " ";

		for(int i = 0; i < this.n; i++){
			execSequence += "P" + safeSequence[i] + " ";
		}

		bufferedReader.close();

		System.out.println(execSequence);
		return true;

	}

	public static void main(String[] args) throws Exception {
		Inspector inspector = new Inspector();

		String path = getPathProject() + File.separator + "data" + File.separator;
		String file = path + args[0];
		String request = path + args[1];

		inspector.readFile(file);

		System.out.println("Dados de entrada: \nArquivo: " + file 
			+ "\nQuantidade de processos: " + inspector.processes.size());

		System.out.println("-----------------------------------");
		System.out.println("Matriz ALLOCATION: ");
		
		inspector.printMatrix(inspector.allocation);

		System.out.println("-----------------------------------");
		System.out.println("Matriz MAX: ");
		
		inspector.printMatrix(inspector.max);

		System.out.println("-----------------------------------");
		System.out.println("Vetor AVAILABLE: ");
		
		inspector.printVector(inspector.available);

		System.out.println("-----------------------------------");
		System.out.println("Matriz NEED: ");
		
		inspector.printMatrix(inspector.need);

		System.out.println("-----------------------------------");
		System.out.println("1. Execucao SAFETY: ");

		inspector.safety();

		System.out.println("-----------------------------------");
		System.out.println("2. Execucao AVOID: ");
		System.out.println("Vetor REQUEST: ");
		
		
		int [] pi = new int[4];
		pi[0] = 1;
		
		pi[1] = 1;
		pi[2] = 0;
		pi[3] = 0;

		inspector.printVector(pi);
		inspector.avoid(pi);

		System.out.println("-----------------------------------");
		System.out.println("3. Execucao DETECTION: ");

		inspector.detection(request);
	}

	private static String getPathProject() {
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }
}
