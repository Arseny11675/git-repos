package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    private static Application app;
    private static Scanner input;
    private static ApiConnector connector;
    public static String City;
    static StringBuilder userSettings = new StringBuilder();//Сохранение выобра пользователя идёт, но я не смог придумать, зачем

    public static void main(String[] args) {
        getInstance().init();
    }

    public Application() {

    }

    public static Application getInstance() {
        if (app == null) app = new Application();
        return app;
    }

    public void init() {
        input = new Scanner(System.in);
        connector = new ApiConnector();
        eneterCity();
        printMenu();
    }

    public void eneterCity() {
    	TalkingWithUser.Greating();
        City = input.next();
    }
    
    public static void changeCity() {
        System.out.print("Please, enter another city: ");
        City = input.next();
        System.out.println();
        userSettings.append(City);
        printMenu();
    }

    public static void printMenu() {
    	TalkingWithUser.printMenuToConsole();
        int option = input.nextInt();
        userSettings.append(option);
        TalkingWithUser.getAnswerFromMenu(option);
    }

    public static void printForecast(ForecastMode mode, GuiCallback callback) {
        if (mode == ForecastMode.TODAY) {
            try {
                System.out.println(connector.getForecastForToday(City));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else 
        if (mode == ForecastMode.TOMMOROW) {
        	try {
                System.out.println(connector.getForecastForTomorrow(City));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        callback.reset();
    }
    
}