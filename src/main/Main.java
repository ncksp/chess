package main;

import utilities.Utilities;

public class Main {
	Utilities util = new Utilities();

	public Main() {
		int choose = 0;
		do {
			choose = util.menu(choose);

			switch (choose) {
			case 1:
				play(1);
				break;
			case 2:
				play(2);
				break;
			}
		} while (choose != 3);
	}

	private void play(int type) {
		// TODO Auto-generated method stub
		System.out.println(type);
		util.scan.nextLine();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
