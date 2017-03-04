package com.ragulbalaji.wordamentbot;

/**
 *      This class holds the main execution code,
 **/
public class BoggleSolver {
        private Dictionary dictionary;
        private int validPaths = 0;
        public BoggleSolver(String fileDir, String letters){
                try {
                        dictionary = new Dictionary(fileDir);
                } catch (Exception e) {
                        System.out.println("Error: "+e);
                        System.exit(0);
                }
                BoggleBoard bBoard = new BoggleBoard(letters);
                bBoard.printBoard();
                System.out.println("=================================================================");
                System.out.println("|               RAGUL BALAJI WORDAMENT BOT 2014                 |");
                System.out.println("=================================================================");
                for(int i = 0; i<bBoard.dimensions(); i++){
                        for(int j=0; j<bBoard.dimensions(); j++){
                                Word wordSoFar = new Word();
                                BoardLocation curLoc = new BoardLocation(i,j);
                                wordSoFar.addLetter(bBoard.letterAtLocation(curLoc),curLoc);
                                Word newWord = wordSoFar.makeCopy();
                                BoggleBoard newBoard = bBoard.makeCopy();
                                newBoard.markAsTaken(curLoc);
                                recurseWords(newWord, newBoard, curLoc);
                        }
                }
                System.out.println("================================================================");
                System.out.println("                    There are "+validPaths+" valid paths.            ");
                System.out.println("================================================================");
        }

        public void recurseWords(Word wordSoFar, BoggleBoard bBoard, BoardLocation loc){
                String str = wordSoFar.toString();
                bBoard.markAsTaken(loc);
                BoardLocation[] adjLoc = bBoard.getAdjacentUntakens(loc);
                if(wordSoFar.length() > 2 && dictionary.containsWord(str)){
                	if(!WordRun.GeneratedWords.contains(str)){
                		if(System.currentTimeMillis() / 1000L >= WordRun.endTime){
                			System.out.println("\nTIME LIMIT EXCEEDED @ "+WordRun.endTime+"; Tried "+ WordRun.WordNo + " Word(s)");
                			System.out.println("\n=== Ragul Balaji 2014 ===\n");
                			System.out.println("Exiting...");
                			System.exit(1);
                		}
                		WordRun.WordNo++;
                		System.out.print("W"+WordRun.WordNo+" : "+str + " {");
                        for(int i = 0; i < wordSoFar.length()-1; i++){
                			//System.out.print("> ("+wordSoFar.myPath[i][0]+","+wordSoFar.myPath[i][1]+") >");
                        	System.out.print(" > "+WordRun.RC2CELL(wordSoFar.myPath[i][0],wordSoFar.myPath[i][1]));
                        	if(i == 0){
                        		WordRun.SWIPE(WordRun.RC2CELL(wordSoFar.myPath[i][0],wordSoFar.myPath[i][1]), WordRun.RC2CELL(wordSoFar.myPath[i+1][0],wordSoFar.myPath[i+1][1]), 1);
                        	}else if(i == wordSoFar.length()-2){
                        		WordRun.SWIPE(WordRun.RC2CELL(wordSoFar.myPath[i][0],wordSoFar.myPath[i][1]), WordRun.RC2CELL(wordSoFar.myPath[i+1][0],wordSoFar.myPath[i+1][1]), 2);
                        	}else{
                        		WordRun.SWIPE(WordRun.RC2CELL(wordSoFar.myPath[i][0],wordSoFar.myPath[i][1]), WordRun.RC2CELL(wordSoFar.myPath[i+1][0],wordSoFar.myPath[i+1][1]), 0);
                        	}
                		}
                        System.out.print(" }   T"+(System.currentTimeMillis() / 1000L - WordRun.endTime)+"s \n");
                        WordRun.GeneratedWords.add(str);
                        validPaths++;
                        WordRun.r.delay(150);
                	}else{
                		System.out.println("Word '"+str+"' repeats... Skipping");
                	}
                }
                if(!dictionary.wordsExistThatStartWith(str)){
                        return;
                }else{
                        for(int i =0; i< adjLoc.length; i++){
                                Word newWord = wordSoFar.makeCopy();
                                newWord.addLetter(bBoard.letterAtLocation(adjLoc[i]),adjLoc[i]);
                                recurseWords(newWord, bBoard.makeCopy(), adjLoc[i]);
                        }
                }

        }

        public static void notmain(String[] args) {
                try{
                        if(args.length != 2){
                                System.out.println("You did not enter enough parameters, please rerun the program.");
                                System.exit(0);
                        }
                        if(!args[0].endsWith(".txt") || args[1].endsWith(".txt")){
                                System.out.println("Please re-execute program with dictionary file as first parameter and string of letters as second parameter sperated by a space. ");
                                System.exit(0);
                        }
                        if((double)(int)Math.sqrt(args[1].length()) == Math.sqrt(args[1].length())){
                                new BoggleSolver(args[0], args[1]);
                        }
                }catch(Exception e){
                        System.out.println("Error: "+e);
                        e.printStackTrace();
                        System.out.println("Please rerun program");
                        System.exit(0);
                }
        }
}
