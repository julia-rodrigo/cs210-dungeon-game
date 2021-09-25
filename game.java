// 17/12/2020
package dungeon;
import java.util.*;
import java.io.*; // need this for timer

/*
 * 
 * I used the software eclipse for this java file, and stored it in a package called 'dungeon'. please comment out package if necessary
 * 
 				sites i used to help me: 
 https://gamedev.stackexchange.com/questions/144834/how-can-i-generate-random-meaningful-words-in-a-typing-game
 https://stackoverflow.com/questions/48610810/how-to-check-the-user-input-is-an-integer-or-not-with-scanner
 https://programming.guide/java/generate-random-character.html
 https://stackoverflow.com/questions/5853989/time-limit-for-an-input

 when putting the code into new class, look at the VERY VERY VERY bottom of the code and make sure that

    gettingUser = (new game()).getInput();

				 ==>(new <This_is_the_name_of_your_class>()).getInput();

                   this class has to be the same as the class right now

	before playing; 1. check the class
					2. Please use your word.txt file
					3. place the word file address in the code
					4. check the gettingUser as instructed above 
					   (you must go to the bottom of the code to fix it)
					5. 'y' and 'k' are buttons mostly used to press to pass through any naration

	you can now play without instructions. what you will be told to do is all in the game. Thanks
*/
//public class treasure_hunting


public class game
{
	private String playerInput = "";			// check user input at exceptions() for timer
	private static boolean onTime = true;		// if user input is place in on time
	private static boolean reisons = false;		// if you got the treasue
	private static boolean escape = false;  	// if you are starting the escape route
	
	public static String word()
	{
		//pls download the words.txt and place address of it here;
		String fileAddressForListOfWords  = "C:\\Users\\julia\\eclipse-workspace\\CS210 PROJECT\\word file2\\words.txt";
		
		String word = getWordFrom(fileAddressForListOfWords).toUpperCase();
		//System.out.println(word); // check the word first if you want to play the game quicky (its cheating tho)
		
		return word;
	}
	
	public static void main (String args[])
	{
		Scanner hello = new Scanner (System.in);
		
		System.out.println("There will be two games here.\n");
		System.out.println("(It's more like a back up for if you can't play the 'Dungeons' one. ");
		System.out.println("But if it happens so, you can play: ' X's and O's ')");
		System.out.println("\nWhat would you like to play: (choose the correct number)\n\n(1) Dungeons\nor\n(2) X's and O's? \n");
		
		String go = "";
		while(!go.equals("1") && !go.equals("2"))
		{
			go = hello.nextLine();
			switch(go)
			{
				case "1": System.out.println ("\nPLAYING: Dungeons_TreasureHunt\n\n"); Dungeons_TreasureHunt(hello, word()); System.exit(0); break;
				case "2": System.out.println ("\nPLAYING: X's and O's\n\n"); XO(hello); System.exit(0); break;
			}
		}
	}
	
	// ------------------ XS AND OS GAME (choose '2')--------------------------
	
	public static void XO(Scanner hello)
	{
		String [][] grid = new String [3][3]; // make a 3 x 3 array
		
		System.out.println("This game will have this grid:"); // instructions
		neutral(grid);
		System.out.println("\nTo place your counter on the grid, type the letter followed by a number (eg 'a1' or 'A1'....)");
		System.out.println("\nInstructions above!\n\n\t\t\t***********\n\n");
		
		String start = start(hello);
		int turn = 1;
		
		boolean okay = false;
		boolean winner = false;
		print(grid);
		
		while(turn <= 9) // 9 turns in the game
		{
			System.out.println("start with player: " + start);
			System.out.println("round: " + turn);
			
			while (okay == false)
				switch(hello.nextLine().toUpperCase()) // counter positions, will only work if "_" (nothing) is in the array
				{
					case "A1": if(nothing(grid[0][0])) { grid[0][0] = start; okay = true;} break;
					case "B1": if(nothing(grid[0][1])) { grid[0][1] = start; okay = true;} break;
					case "C1": if(nothing(grid[0][2])) { grid[0][2] = start; okay = true;} break;
					case "A2": if(nothing(grid[1][0])) { grid[1][0] = start; okay = true;} break;
					case "B2": if(nothing(grid[1][1])) { grid[1][1] = start; okay = true;} break;
					case "C2": if(nothing(grid[1][2])) { grid[1][2] = start; okay = true;} break;
					case "A3": if(nothing(grid[2][0])) { grid[2][0] = start; okay = true;} break;
					case "B3": if(nothing(grid[2][1])) { grid[2][1] = start; okay = true;} break;
					case "C3": if(nothing(grid[2][2])) { grid[2][2] = start; okay = true;}
				}
			
			okay = false;
			turn++;
			
			print(grid); // print the grid
			
			if(turn > 5) winner = winner(grid, start); // start checking for the winner when the turn becomes greater than 5
			
			if(winner) 
			{
				System.out.println("\nPLayer " + start + " is the winner!");
				System.exit(0); // end if there is winner
			}
			
			start = switching(start); // switch counters
			System.out.println("");
		}
		
		System.out.println("Oof there is no winner haha =)");
	}
	
	// -------------cheking for winner ---------
	
		public static boolean winner(String[][] grid, String start) // check all winning conditions
		{
			//System.out.println(start);
			if(grid[0][0].equals(start) & grid[0][1].equals(start) & grid[0][2].equals(start)) return true;
			else if(grid[1][0].equals(start) & grid[1][1].equals(start) & grid[1][2].equals(start)) return true;
			else if(grid[2][0].equals(start) & grid[2][1].equals(start) & grid[2][2].equals(start)) return true;
			
			else if(grid[0][0].equals(start) & grid[1][0].equals(start) & grid[2][0].equals(start)) return true;
			else if(grid[0][1].equals(start) & grid[1][1].equals(start) & grid[2][1].equals(start)) return true;
			else if(grid[0][2].equals(start) & grid[1][2].equals(start) & grid[2][2].equals(start)) return true;
			
			else if(grid[0][0].equals(start) & grid[1][1].equals(start) & grid[2][2].equals(start)) return true;
			else if(grid[0][2].equals(start) & grid[1][1].equals(start) & grid[2][0].equals(start)) return true;
			
			
			return false;
		}
	
	// -- chedkcing grid pos-----------
	
	public static boolean nothing(String pos)
	{
		if(pos.equals("_")) return true;
		else return false;
	}
	
	// ----------- print the grid -----------------
	
	public static void print(String [][] grid)
	{
		int number = 1;
		System.out.println(" A B C");
		for(int i = 0; i < 3; i++) 
		{
			System.out.print("|");
			for(int a = 0; a < 3; a++)  System.out.print(grid[i][a] + "|");
			System.out.print(number + "\n");
			number++;
		}	
	}
	
	// --------- neutral grid -------
	
	public static void neutral(String [][] grid)
	{
		int number = 1;
		System.out.println(" A B C");
		for(int i = 0; i < 3; i++) 
		{
			System.out.print("|");
			for(int a = 0; a < 3; a++) 
			{
				grid[i][a] = "_";		
				System.out.print(grid[i][a] + "|");
			}
			System.out.print(number + "\n");
			number++;
		}	
	}
	
	// ----------- change player turn ------------
	
	public static String switching(String who)
	{
		if(who.equals("X")) return "O";
		else return "X";
	}
	
	// -------------- choose x or o to start ----------------
	
	public static String start(Scanner hello)
	{
		String start = ""; 
		while(!start.equals("X") && !start.equals("O")) 
		{
			System.out.println("Would you like to start with 'x' or 'o':\nChoose 'x' or 'o'");
			start = hello.nextLine().toUpperCase();
			//System.out.println("start: " + start);
		}
		return start;
	}
	
	
	
	// --------------------other game---------------
	
	// --------------------Dungeons-----------
	
	
	// ------------------ (choose '1') ---------------------------------
	
	
	
	
	public static void Dungeons_TreasureHunt(Scanner hello, String word)
	{
			System.out.println("Enter 'y' or 'Y' to continue:");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			
			intro(hello);
			String doors = doors(hello, word);
			
			System.out.println("Enter 'k' or 'k' to continue:");
			while(!hello.nextLine().toLowerCase().equals("k")) {}
			
			System.out.println("You have " + doors + " reisons!");
			
			//--------escape route
			escape(hello, Integer.parseInt(doors));
			// finished!!!!!!!!
	}
	
	// ----------------------- -------- finish game! -------------------------------------------- (31)
	
	public static void goodbuyAndGoodbye(Scanner hello)
	{
		if(bought)
		{
			System.out.println("Enter 'y' or 'y' to continue:\n");
			
			System.out.println("Omg thank you for buying from me!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("You are very kind haha");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("I know all those merchandise will be in good hands");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
		}
		else
		{
			System.out.println("\n\n\nHaha XD ok no ('y' to continue)");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("Anyways, ");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("Woah!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("What a day!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("Such as what we have accomplished today was an experience to behold");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
		}
			if(reisons) System.out.println("Be sure to keep your treasures safe.");
			System.out.println("Alas, it is now time for us to part");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("We have a long journey ahead into winter");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("Ima kinda feeling emotional");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("But i shant be worried!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("Nous allons rencontrer à nouveau mon ami!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("I am certain!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			
			ty(hello);
	}
	
	// -------------------------------------------- display items --------------------------------------------------------- (30)
	
		public static void display(Scanner hello)
		{
			System.out.println("(a) Flying Mansion with Air Hockey:		10000 reisons");
			System.out.println("(b) Poof-ish Unicorn without horn:		20000 reisons");
			System.out.println("(c) Jujitsei Fox that purrs:			30000 reisons");
			System.out.println("(d) Alexi pegasus without winges:		40000 reisons");
			System.out.println("(e) Good Luck Serpent that can't hiss:		50000 reisons");
			System.out.println("(f) SIX headed Hydra without vision:		60000 reisons");
			System.out.println("(g) Jack Pheonix that has one life:		70000 reisons");
			System.out.println("(h) Silver Griffin with no lion resemblence:	80000 reisons");
			System.out.println("(i) Apple Mix Dragon that can change colour:	90000 reisons");
			System.out.println("(j) Homemade Choco Cookies:			    2 reisons ");
		}
	
	
	// ----------------------------------- help for selecting -------------------------------------------------------------- (29)
	
	public static void help()
	{
		System.out.println("Pst pst!\n(press 'v' for display of items)");
		System.out.println("(Enter the correct letter beside the display to choose)");
		System.out.println("(Enter 'n' to cancel)\n");
	}
	
	// --------------------- when player  enters amount of items they want, this checks if input is number ------------- (28)
	
	public static int amount(Scanner hello)
	{
		int playerInput = 0;
		boolean number = false;													// ony true when number entered
		while(!number)
			try
			{
					playerInput = hello.nextInt(); 
			        hello.nextLine();
			        number = true;
			} 
			catch(InputMismatchException ime) { hello.nextLine(); }				// skip scanner bug
		return playerInput; 
	}
	
	// --------------------------------------------- buy the item ------------------------------ --------------------- (27)
	
	public static int buyingItem(Scanner hello, int cost, int treasure, String item)
	{
		System.out.println("You can buy more! How much would you like?");		// how much you want?	
		int amount = amount(hello);												// check if player input a number
		if(amount > 0)															// if you have enough, then you can buy
		{
			System.out.println("That would be " + cost*amount + " reisons!");
			System.out.println("Are you sure? ('y' for yes, or 'n' for no)");
			if(yesOrNo(hello))
			{
				if (treasure - cost*amount >= 0 ) { bought = true; return treasure - cost*amount; }
				else System.out.println("I'm sorry.. it seems that You don't have enough\n");
			}
			else 
			{
				System.out.println("You didn't buy the " + amount + " " + item + "...");
				System.out.println("But thats okay!\n");
			}
		}
		return treasure;														// return how much money left
	}
	
	// --------------------------------- if you want something from the shop ------------------------- ------------ (26)
	
	public static int want(Scanner hello, String item, int cost, int treasure)
	{
		if(treasure >= cost) 										// if you have enough
		{
			System.out.println("You want the " + item + "?"); 		// if you want it
			if(yesOrNo(hello)) {treasure = buyingItem(hello, cost, treasure, item); };	// then proceed to buyingItem()
		}
		else System.out.println("I'm sorry.. it seems that You don't have enough\n"); // not enough so leave
		
		System.out.println("You have " + treasure + " reisons left!");	// 'reisons' is a currency i made up lol
		return treasure;
	}
	
	// ------------------------------------ in shop -------------------------------------------------------------- (25)
	
	public static boolean bought = false;				
	
	public static void shop(Scanner hello, int treasure)
	{
		boolean sure = false;
		while(!sure)
		{
			System.out.println("Choose which one you'd like! (press 't' for help) ");
			String buyer = hello.nextLine().toLowerCase();
			switch (buyer)
			{
				case "t" : help(); break;				// need help with instructions on how to select or leave
				case "v" : display(hello); break;		// display items again
				case "a": treasure = want(hello, "Mansion", 10000, treasure); break; 	// if you buy something, treasure will be reducted
				case "b": treasure = want(hello, "Unicorn", 20000, treasure); break;	// go to method want() if you choose something
				case "c": treasure = want(hello, "Fox", 30000, treasure); break;
				case "d": treasure = want(hello, "Pegasus", 40000, treasure); break;
				case "e": treasure = want(hello, "Serpent", 50000, treasure); break;
				case "f": treasure = want(hello, "Hydra", 60000, treasure); break;
				case "g": treasure = want(hello, "Pheonix", 70000, treasure); break;
				case "h": treasure = want(hello, "Griffin", 80000, treasure); break;
				case "i": treasure = want(hello, "Dragon", 90000, treasure); break;
				case "j": treasure = want(hello, "Delicous Handmade yummy Cookies", 2, treasure); break;
				case "n": System.out.println("You want to leave the shop? Are you sure? ('y' for yes, or 'n' for no)"); 
					if(yesOrNo(hello)) sure = true; 
			}
		}
		System.out.println("Enter 'k' or 'K' to continue:");
		while(!hello.nextLine().toLowerCase().equals("k")) {}
		
		goodbuyAndGoodbye(hello);									// ending of game
	}

	// -------------------------------- buying from merchant ------------------------------------------- ----------- (24)
	
	public static void buyFromMerchant(Scanner hello, String treasure)
	{
		System.out.println("\nHere's what I have: (Press 'y' to continue)\n");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		display(hello);																// display the items on sale
		
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		boolean sure = false;
		while (sure == false)										// if you want to buy any, enter 'y' or 'n' only for options
		{
			System.out.println("Would you like any =D ? ('y' for yes, or 'n' for no)");
			if(yesOrNo(hello))
			{
				shop(hello, Integer.parseInt(treasure));			// go to shop()
				sure  = true;
			}
			else
			{
				System.out.println("Are you sure D= ? ('y' for yes, or 'n' for no)");
				if(yesOrNo(hello)) sure = true;
			}
		}
	}
	
	
	// --------------------------- only escape for 'n' or 'y' ------------------------------------------- (23)
	
	public static boolean yesOrNo(Scanner hello)
	{
		while(true)
		{
			String result = hello.nextLine().toLowerCase();
			if(result.equals("y")) return true;
			else if (result.equals("n")) return false;
		}
	}
	// --------------------------------- finsihed escape() with different conditions ----------------------- (22)
	
	public static void YouDidIt(Scanner hello, String treasure)
	{
		System.out.println("OMG you did it! ('y' to continue)");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("That was a hard treasure hunt!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("And very dangerous!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		System.out.println("Ima so prouda you. Tres bien~");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		if (Integer.parseInt(treasure) > 0) 											// if you got a lot of treasure
		{
			System.out.println("Look at all those treasure you've gotten!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println(treasure + "k treasure!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("You need to reward yourself! Bien sur!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			
			System.out.println("I am actually a well-known merchant... from a faraway land!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			
			System.out.println("How about you buy from me, yes?");
			
			while(yesOrNo(hello) == false)
			{
				System.out.println("C'mon, please just have a look");
			}
			System.out.println("\n(Press 'k' to continue)");
			while(!hello.nextLine().toLowerCase().equals("k")) {}
		}
		else
		{
			if(reisons)																// if you got the treasure, but lost it all
			{
				System.out.println("Too bad you drop the the treasure =(");
				while(!hello.nextLine().toLowerCase().equals("y")) {}
			}
			else																	// if you couldn't get the final code
			{
				System.out.println("Too bad we couldnt code the treasure =(");
				while(!hello.nextLine().toLowerCase().equals("y")) {}
			}
			
			System.out.println("But you know what? =}");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("At least we are both safe =}");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("=) And there is always a next time!");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("(And there are other means of getting them!)");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			System.out.println("Anyways,");
			while(!hello.nextLine().toLowerCase().equals("y")) {}
			ty(hello);																// end the game
		}
	}
	
	// ----------------------------------------- ty for playing -------------------------------------------------------- (21)
	
	public static void ty(Scanner hello)
	{
		System.out.println("Thank you for joining me on this quest!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("Yeah you have fun now!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("And I hope you had a very Merry Christmas");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("And a Happy New Year too");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("Thank you for this Semester! =)");
		System.exit(0);
	}
	
	// ----------------------- when you pass the doors, this is the escape route --------------------------------------------(20)
	
	public static void escape(Scanner hello, int treasure)
	{
		escape = true;
		System.out.println("\nPress 'k' or 'K' to continue");
		while(!hello.nextLine().toLowerCase().equals("k")) {}
		look(hello);													 // start with naration
		String gems = ladderLables(hello, treasure);					// start the ladder game
		System.out.println("Press 'k' or 'K' to continue");
		while(!hello.nextLine().toLowerCase().equals("k")) {}
		YouDidIt(hello, gems);											// end of ladder game
		if (Integer.parseInt(gems) > 0) buyFromMerchant(hello, gems);	// if you treasure, you can buy something
		goodbuyAndGoodbye(hello);										// the end
		System.out.println("\n");
	}
	
	// ---------------------------------- lable the ladder properly -----------------------------------------------------(19)
	
	public static String ladderMiddle(String wwr)
	{
		String ladderMiddle = "     ";
		for(int i = 0; i < wwr.length(); i++) ladderMiddle = ladderMiddle + "|" +  wwr.charAt(i) + "|    ";
        return ladderMiddle;
	}
	
	// ------------------------ choose right letter ------------------------------------------------------------------- (18)
	
	public static boolean wwr(String wwr, String run, String y)
	{
		if(onTime) // if player entered on time, 
			for(int i = 0; i < run.length(); i++) // look through the RUN and if the player choose the right char, you dont loose point
	        	if(y.equals("" + run.charAt(i)) & (wwr.charAt(i) == ' ')) return true; 
        return false;
	}
	
	// ---------------------------- shuffle "x x _" in the ladder and return string  --------------------------------(17)
	
	public static String shuffleLadders(String toShuffle)
	{
        List<Character> characters = new ArrayList<Character>();
        for(char c: toShuffle.toCharArray()) characters.add(c);
        
        StringBuilder shuffled = new StringBuilder(toShuffle.length());
        while(characters.size()!=0)
        {
            int pickHere = (int)(Math.random()*characters.size());
            shuffled.append(characters.remove(pickHere));
        }
        String shuffling = shuffled.toString();
        return shuffling;
	}
	
	// ------------------------------------------------- start ladder game ---------------------------------------------------------- (16)
	
		public static String ladderLables(Scanner hello, int treasure)
		{
			int i = 0, lost = 0, life = 5;
			String run = "RUN", wrongwrongright = "xx ";	// the keys will be R, U , N and the wrong ladders have an 'x' in them
			Random woah = new Random();
			
			while(i < 10)
			{
				System.out.println("You have " + life + " Life Points left!!");
				String gettingUser = "";
				String wwr = shuffleLadders(wrongwrongright); 						// shuffle this string
				System.out.println("Which ladder is it? (enter the right letter)");
				System.out.println("     |_|    |_|    |_|");
				System.out.println("     |_|    |_|    |_|");
				System.out.println(ladderMiddle(wwr));								// lable the ladders
				System.out.println("     | |    | |    | |");
				System.out.println("      R      U      N");
				gettingUser = exception(gettingUser).toUpperCase();					// player input
				if(wwr(wwr, run, gettingUser) == false) // if player is wrong
				{
					life--;
					if(life == 0) gameOverLadderVer(hello); // game over if life is zero
					onTime = true;
					if(reisons == true) // variable to check if you have treasure
					{
						lost = woah.nextInt(259999);								// lose treasure (if you have any)
						System.out.println("You lost " + lost + " treasure out of panic! ");
						treasure -= lost;
						if(treasure < 0) treasure = 0;
					}
					else System.out.println("OMG! Watch your step!!... hurry!"); // or else this
				}
				if(life == 0) gameOverLadderVer(hello);
				i++;
			}
			
			return "" + treasure;
		}
		
	
	
	
	// ------------------------------------- naration and instructons to escape the dungeon ----------------------------- (15)
	
	public static void look(Scanner hello)
	{
		escape = true;
		System.out.println("We need to escape the dungeon!");
		System.out.println("Press 'y' or 'Y' to continue");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		System.out.println("There are rope ladders over there!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		System.out.println("     |_|    |_|    |_|");
		System.out.println("     |_|    |_|    |_|");
		System.out.println("     |x|    |x|    | |");
		System.out.println("     | |    | |    | |");
		System.out.println("      R      U      N");
		
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("Someone mustve been here before! (but that doesnt matter)");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("Some of the ladders are useless!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("We need to pick up the correct one!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		System.out.println("And we need to do it FASTER then ever (timer is 2 seconds now)");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("lol");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("(choose the correct ladder by typing the right letter)");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		System.out.println("the letters are either R, U, or N");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		System.out.println("Ready Mate? (Press 'k' to continue)");
		while(!hello.nextLine().toLowerCase().equals("k")) {}

		System.out.println("\n");
	}
	
	/// -----------------------------
	
	
	
	// ======================================== Skip =================================================== (31) (32)
	
	public static void gameOverDoorsVer(Scanner hello)
	{
		System.out.println("Oh no, .... y");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("...");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("... thump thump thump...");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("\n  __ --- __");
		System.out.println("  |[_____]| ");
		System.out.println("  <|X_ _X|>_____");
		System.out.println("   |  Y  | ||XX/");
		System.out.println("  /       ~||");
		System.out.println(" | |     | ||");
		System.out.println(" --/X X X=--|");
		System.out.println(" -/   __ ^-");
		System.out.println(" -|  /--|__|");
		System.out.println(" =___=== --");
		
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("It caught up to us....");
		System.exit(0);
		
	}
	public static void gameOverLadderVer(Scanner hello)
	{
		System.out.println("Its pulling the ladder! No don't----.....y");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("...");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("... thump thump thump...");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("\n  __ --- __");
		System.out.println("  |[_____]| ");
		System.out.println("  <|X_ _X|>_____");
		System.out.println("--/|  Y  |~||XX/");
		System.out.println(" | </////> ||");
		System.out.println("_|W_______W__");
		System.out.println("---------------");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("How...did it get to us so quick......");
		System.exit(0);
		
	}
	
	// ======================================================= Skip =============================================================
	
	
	
	// -------------- now planning searching finish (moving to escape phrase)------------------------------- /////////////////////////////
	
	
	
	
	
	//--------------------------------------------------------- enter treasure code -------------------------------------- ------- (14)
	
	public static int treasure(String code, String word, int life, Scanner hello)
	{
		passedDoors(hello, word);
		
		int attempts = 3;
		System.out.println("Heres what we have writen: ");
		System.out.println("\n  " + code + "\n\n(enter 'k' to continue)");
		while(!hello.nextLine().toLowerCase().equals("k")) {}
		
		if(code.equals(word)) // if you entered all the correct doors
		{
			System.out.println("We need to hurry and write it down!: ");
			System.out.println("Write down the code: (you have 3 attempts)");
			while(!hello.nextLine().toUpperCase().equals(word)) 			// the  player types in the correct word with 3 attempts
			{
				attempts --;
				if(attempts == 0) 											// if not right by third attempt, then continue with no treasure
				{
					System.out.println("... we cant.. we've got to go!");
					return 0;
				}
				System.out.println("That's not the right one! (you have " + attempts + " attempts left!)");
			}
		}
		else				// if your code is missing some letters, you'll have to decode it
		{
			System.out.println("We need to hurry and decode it!: "); 
			System.out.println("Do you have any idea what it may be?: (you have 3 attempts)");
			while(!hello.nextLine().toUpperCase().equals(word))
			{
				attempts --;
				if(attempts == 0) 
				{
					System.out.println("... we cant.. we've got to go!");
					return 0;
				}
				System.out.println("Thats not the right one! (you have " + attempts + " attempts left!)");
			}
		}
		reisons = true;
		System.out.println("YOU DID IT!! omg look !!! so much treasure!\n");
		return 1000000;
	}
	
	// ------------------------------------------------------- naration when you pass the doors -------------------------------- (13)
	
	public static void passedDoors(Scanner hello, String word)
	{
		System.out.println("Enter 'y' or 'Y' to continue:");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("We did it! omg look at that!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("Its a treasure chest!!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("...");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("But its stuck to the ground..");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		
		System.out.println("Look! here it seems if we enter a code");
		System.out.println("we might be able to unlock it and take it with us!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("this code has " + word.length() + " letters. ");
		
		System.out.println("We need to enter a word with " + word.length() + " letters");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("Hey! didnt we see letters on the doors");
		System.out.println("we've chosen to get here?");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
	}
	
	//---------------------------------------------------- math --------------------------------------------------------------------- (12)
	
	public static int math(Random woah)
	{
		int a = woah.nextInt(50); // get a random math addition question equation
		int b = woah.nextInt(50);
		System.out.println("\n      " + a + " + " + b + " = ...?" ); // print it to the top
		
		return a + b;				// return the answer sent to the doorlables() method
	}
	
	// -------------------------------------------------------- print doors ------------------------------------------------ (11)
	
	public static void printDoors(String numbers, String letters)
	{
		System.out.println(letters); 							// letters here
		System.out.println("   |   |  |   |  |   |");
		System.out.println(" __|   |__|   |__|   |__");
		System.out.println(numbers); 							// numbers here
	}
	
	// ---------------------------------------------------- shuffle strings ----------------------------------------------------- (10)
	
	public static void shuffle(String toShuffle, int answer, Random woah, char right)
	{
        List<Character> characters = new ArrayList<Character>(); // a list of characters
        for(char c: toShuffle.toCharArray()) 					 // turns string to shuffle to a char array
            characters.add(c); 									 // ands push it to the list
        StringBuilder output = new StringBuilder(toShuffle.length()); // capacity of string length
        while(characters.size()!=0)
        {
            int randPicker = (int)(Math.random()*characters.size()); // gets a random char
            output.append(characters.remove(randPicker)); 		 // and puts it into the string builder
        }
        String shuffling = output.toString(); // converts string builder to string
        String shuffled = "    ";	// now Going to label the doors with char
        String choose = "     ";  // Going to label the doors with numbers
        int again = 0;
        for (int i = 0; i < shuffling.length(); i++)
        {
        	shuffled = shuffled + "_" + shuffling.charAt(i) + "_    ";
        	if(shuffling.charAt(i) == right)  					// if the characters is the correct one, 
        		choose = choose + answer + "      "; 			// put the right number under it
        	else
        	{
        		again = woah.nextInt(101);						// else put random numbers over the other random chars
        		while(again == answer)
        			again = woah.nextInt(101);					// if the answer and this random number is the same, shuffle again
        		choose = choose + again + "     ";
        	}
        }
        printDoors(choose, shuffled);
    }
	
	// ----------------------------------------------- labbling doors --------------------------------------------------------------- (9)
	
		public static void doorLables(char right, Random woah, int answer)
		{
			char wrong1 = (char)('A' + woah.nextInt(26));			// get a random characters
			char wrongAgain = (char)('A' + woah.nextInt(26));		// get another random character
			String toShuffle = "" + wrong1 + wrongAgain + right;	// put them together and bibiddibopidiboo
			shuffle(toShuffle, answer, woah, right);				// shuffle them and the right number answer
		}
	
	// -------------------------------------- check letter if the same ----------------------------------------------------  (8)
	
	public static boolean letter(Scanner hello, String y, String gettingUser)
	{
		if(onTime & gettingUser.toUpperCase().equals(y)) return true;
		return false;
	}
	
	// ------------------------------------ after naration , choose the doors -----------------------------------  (7)
	
	public static String doors(Scanner hello, String word)
	{
		int i = 0, life = 3, answer = 0;
		String code = "";      Random woah = new Random();
		while(i < word.length())
		{
			if(life == 0) gameOverDoorsVer(hello);
			else System.out.println("You have " + life + " Life Points! Be careful!");
			
			String gettingUser = "";
			System.out.println("Which door is it? (enter the right letter)"); // enter the correct door
			
			answer = math(woah); // Randomize math equation and get answer
			doorLables(word.charAt(i), woah, answer); // Label the doors
			
			gettingUser = exception(gettingUser); // get exception from user
			
			// if its the corrrect letter, then put the letters in the code
			if(letter(hello, "" + word.charAt(i), gettingUser.toUpperCase())) code = code + "" + word.charAt(i);
			else
			{
				life--; 			// reduce life
				onTime = true;		// on time becomes true for the next question
				code = code + "*";	// missed a word from the code
			}
			i++;
		}
		System.out.println("life: " + life);
		
		if(life == 0) { gameOverDoorsVer(hello); } // if life runs out , gameOver
		
		if(treasure(code, word, life, hello) == 0) // if you didnt get the treasure
		{
			System.out.println("Hurry! we have no time! We need to escape now!"); escape = true; 
			return "0";
		}
		System.out.println("Hurry! we have no time! We need to escape now!"); // if you got the treasure
		escape = true;
		return "1000000";
		
	}
	
	
	// ---------------------------------------- intro ----------------------------------------------------------------- (6)
	public static void intro(Scanner hello) // intro for game!
	{
		System.out.println("Down in the dungeons, there is treasure!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("Let's search for this treasure!");
		while(!hello.nextLine().toLowerCase().equals("y")) {}
		System.out.println("Because it will be fun! Ready? (enter 'k' or 'K')");
		while(!hello.nextLine().toLowerCase().equals("k")) {}
	}
	
	/// -------------------------------------------- timer task ------------------------------------------------------	(5)
	
	TimerTask GettingCloser = new TimerTask() // getting closer task and then life points reduce
    {
        public void run()
        {
            if( playerInput.equals("") ) // is the player didn't enter anything in
            {
            	onTime = false; // if they entered on time
            	if(escape) // during escape this will be used, this is the ladder version
            	{
            		System.out.println("\nThe ladder... Something is also climbing up with us...");
	                System.out.println("Hurry! We have to keep going! (Type anything to continue)");
            	}
            	else // this is the door version
            	{
	                System.out.println("\nThe ground... It's shaking. Almost like Footsteps...");
	                System.out.println("Hurry! We have to keep going! (Type anything to continue)");
                }
            }
        } 
    };
    
	//----------------------------------- -----------timer ---------------------------------------------------- (4)
    
	private static int timing = 10*1000; // timer during first half of game
	
	public String getInput() throws Exception
    {
		if(escape) timing = 2*670;	//	during escape
        Timer timer = new Timer();
        timer.schedule( GettingCloser, timing ); // if your time runs out, something gets closer...
        BufferedReader book = new BufferedReader( new InputStreamReader( System.in ) ); // countdown starts
        playerInput = book.readLine(); // waiting for user input
        timer.cancel();
        return playerInput; // bring the string back down to exception() method to check; 
    }
	
	//--------------------------------- file location ------------------ and get word------------------------------------------- (3)
	
	public static String getWordFrom(String fileAddressForListOfWords)
	{
		File theList = new File(fileAddressForListOfWords);
        List<String> words = new ArrayList<>(); // declare an array to keep the words
        Scanner hello = null;					//scanner not given a task
        try {  hello = new Scanner(theList); } // scan the list only
        catch (FileNotFoundException e) 		// if the list isn't found
        {  System.out.println("file \"" + fileAddressForListOfWords + "\" not found \n(pls play the other game)"); System.exit(0); }
        while(hello.hasNextLine()) 
        {
        	//System.out.println("here1");
            String word = hello.nextLine(); // to next word as scanner goes through list
            words.add(word);				// add words into the array
        }
        String thisWord = randomWord(words); // select a word randomly
        return thisWord;

	}
	
	// -------------------------------------- gets a random word from the list --------------------------------------------(2)
	
	private static String randomWord(List<String> words) 
	{
		 Random rand = new Random();
        int size = words.size(), place; // size of the list and the plce in that list we will get the code from
        place = rand.nextInt(size); // randomly select any place in the list
        String s = words.get(place); // gets the word in that place
            
        return s;
    }
	// --------------------------- catch the exception as user string inputs string during timer -----------------------------(1)
	
	public static String exception(String gettingUser)
	{
		try{ gettingUser = (new game()).getInput(); }// this has to be the the class its in ...GET INPUT method
        catch( Exception e ) { System.out.println( e ); }
		return gettingUser;
	}
}
