package eci.arsw.covidanalyzer;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class CovidThread extends Thread{
	private List<File> covidFiles;
	private AtomicInteger amountOfFilesProcessed;
	private TestReader testReader;
	private ResultAnalyzer resultAnalyzer;
	private boolean enPausa;
	
	
	public CovidThread(List<File> covidFiles, AtomicInteger amountOfFilesProcessed, TestReader testReader,ResultAnalyzer resultAnalyzer) {
		this.covidFiles = covidFiles;
		this.amountOfFilesProcessed = amountOfFilesProcessed;
		this.testReader = testReader;
		this.resultAnalyzer = resultAnalyzer;
	}
	
	public void run(){
		for (File resultFile : covidFiles) {
			synchronized(this){
				while (isEnPausa()){
					try {
						wait();                                
					} catch (InterruptedException ex) {
						ex.printStackTrace();
                    }
                }
		}
         List<Result> results = testReader.readResultsFromFile(resultFile);
         for (Result result : results) {
             resultAnalyzer.addResult(result);
         }
         amountOfFilesProcessed.incrementAndGet();
		}
	}
		
	public void restart() {
		this.setEnPausa(false);
		synchronized(this) {
			this.notifyAll();
		}
	}
	
	public boolean isEnPausa() {
		return enPausa;
	}

	public void setEnPausa(boolean enPausa) {
		this.enPausa = enPausa;
	}
}
