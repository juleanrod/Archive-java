import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;

public class SalesAnalysisDemo {
	
	public static void main(String[] args) {
		processesInputFile("SalesData.txt");
	}
	
	public static void processesInputFile(String fileName) {
		Scanner inputFile = null;
		try {
			File file = new File(fileName);
			if (file.exists()) {
				int weekCount = 0;
				inputFile = new Scanner(file);
				while (inputFile.hasNextLine()) {
					inputFile.nextLine();
					weekCount++;
				}
				inputFile.close();
				double weeklySales[][] = new double[weekCount][7];
				inputFile = new Scanner(file);
				weekCount = 0;
				while (inputFile.hasNextLine()) {
					double dailySales[] = new double[7];
					String line = inputFile.nextLine();
					String lineArray[] = line.split(",");
					for (int i=0;i<lineArray.length;i++) {
						dailySales[i] = Double.parseDouble(lineArray[i].trim());
					}
					weeklySales[weekCount] = dailySales;
					weekCount++;
				}
				inputFile.close();
				displayData(weeklySales);
			} else {
				System.out.println("Input File not found");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (inputFile != null) {
					try {
						inputFile.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	
	public static void displayData(double weeklySales[][]) {
		
		double totalSales = 0;
		double avgWeeklySales = 0;
		double totalWeeklySales[] = new double[weeklySales.length];
		
		for (int i=0;i<weeklySales.length;i++) {
			double totalWeeklySale = 0;
			double avgDailySales = 0;
			for (int j=0;j<weeklySales[i].length;j++) {
				totalWeeklySale = totalWeeklySale + weeklySales[i][j];
			}
			totalWeeklySales[i] = totalWeeklySale;
			totalSales = totalSales + totalWeeklySale;
			avgDailySales = totalWeeklySale/7;
		
			System.out.printf("Weekly sales from week %d is $%.2f\n", (i+1), totalWeeklySale);
			System.out.printf("Average for week %d is $%.2f\n", (i+1), avgDailySales);
		}
		
		avgWeeklySales = totalSales/weeklySales.length;
		System.out.printf("Total sale of all weeks = %.2f\n", totalSales);
		System.out.printf("Average weekly sales = %.2f\n", avgWeeklySales);
		double highestSale = totalWeeklySales[0];
		int highestSaleWeek = 0;
		double lowestSale = totalWeeklySales[0];
		int lowestSaleWeek = 0;
		
		for (int i=0;i<totalWeeklySales.length;i++) {
			if (totalWeeklySales[i] > highestSale) {
				highestSale = totalWeeklySales[i];
				highestSaleWeek = i;
			}
			if (totalWeeklySales[i] < lowestSale) {
				lowestSale = totalWeeklySales[i];
				lowestSaleWeek = i;
			}
		}
		System.out.printf("The week number with the highest amount of sales is: %d\n", highestSaleWeek+1);
		System.out.printf("The week number with the lowest amount of sales is: %d", lowestSaleWeek+1);
	}
}
