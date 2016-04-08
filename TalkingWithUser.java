package com.company;

import java.io.IOException;

/*
 * Задание было:
 * 6) Вынести логику пользовательского интерфейса из Main class в другой класс.
 * Что это вообще значит? Ну, вывел три функции, хотя стало разве что
 * сложнее воспринимать код.
 */

public class TalkingWithUser {
	
	public static void printMenuToConsole(){
		System.out.println("Menu:");
		System.out.println("\t1 - get weather forecast for today");
		System.out.println("\t2 - get weather forecast for tomorrow");
		System.out.println("\t3 - changing city");
		System.out.println("\t4 - exit programm");
	}
	
	public static void Greating() {
        System.out.println("Hello from the OpenWeatherMap API sample app!\n" +
        		"Please, enter city, where you live. Only so we can give you forecast.");
	}
	
	public static void getAnswerFromMenu(int choice){
		for (Choice ch : Choice.values()){ //Единственный способ использовать перечисления в вводе ответа пользователя,
			if( ch.ordinal() == choice-1 ) { //который я придумал. Если ответ не 1-4, то программа ломается.
				switch (choice) {			//Что нужно было сделать на самом деле?
        			case 1: Application.printForecast(ForecastMode.TODAY, new GuiCallback() {
        					@Override
        					public void reset() {
        						Application.printMenu();
        					}
        				});
        				break;
        			case 2: Application.printForecast(ForecastMode.TOMMOROW, new GuiCallback() {
        					@Override
        					public void reset() {
        						Application.printMenu();
        					}
        				});
        				break;
        			case 3:
        				Application.changeCity();
        				break;
        			case 4:
        				System.out.println("Thanks for using!");
        				System.exit(0);
        				break;
				}
			}
		}
	}
}
