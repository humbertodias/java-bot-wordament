package com.ragulbalaji.wordamentbot;

import java.awt.AWTException;
import java.awt.List;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordRun {

	public static String letters = "";

	public static int tileSize = 100;
	public static int SX = 50, SY = 200;
	public static int PosX,PosY;

	public static long startTime = System.currentTimeMillis() / 1000L;
	public static long endTime = startTime + 100;
	public static long inputRunTime;

	public static int WordNo = 0;

	public static ArrayList<String> GeneratedWords = new ArrayList<String>();

	public static Robot r;

	public static void main(String[] args) {
		System.out.println("Wordament Bot (C) Ragul Balaji 2014");
		Scanner in = new Scanner(System.in);

		System.out.print("GRID DATA:");
		letters = in.nextLine();

		System.out.print("Run Time (s):");
		inputRunTime = in.nextInt();

		startTime = System.currentTimeMillis() / 1000L;
		endTime = startTime + inputRunTime;

		System.out.println("Started at "+startTime+" and Scheduled to end at "+endTime);
		//GeneratedWords.add("TEST");
		//System.out.print(GeneratedWords.contains("TEST2"));
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		r.delay(250);
		new BoggleSolver("words.txt", letters);

		//SWIPE(15,10,1);
		//SWIPE(10,11,2);
	}

	public static int RC2CELL(int r,int c){
		return (r*4)+c;
	}

	public static void SWIPE(int start,int end,int press){
		r.mouseMove(SX+(start/4)*tileSize, SY+(start%4)*tileSize);
		if(press == 1){
			r.mousePress(InputEvent.BUTTON1_MASK);
		}
		r.delay(25);
		r.mouseMove(SX+(end/4)*tileSize, SY+(end%4)*tileSize);
		r.delay(25);
		if(press == 2){
			r.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		r.delay(50);
	}
}
