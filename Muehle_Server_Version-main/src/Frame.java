import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class Frame extends JFrame{
    private boolean logicalColour;
    private boolean playerColour;
    private boolean gameOver;
    private int amountOfUnusedWhiteStones;
    private int amountOfUnusedBlackStones;
    private int amountOfWhiteStonesOutOfGame;
    private int amountOfBlackStonesOutOfGame;
    private boolean aMillWasCreatedInThePreviousAction;
    private boolean stoneIsSelected;
    private Feld selectedStone;
    private boolean itsYourTurn;
    private boolean changePlayer;
    private List<Feld> whiteStonesOnBoard;
    private List<Feld> blackStonesOnBoard;
    private List<Feld> whiteStonesOutOfGame;
    private List<Feld> blackStonesOutOfGame;
    private Feld[][] fields = new Feld[3][8];

    private GameHandler opponentGameHandler;
    private GameHandler gameHandler;
    private ClientHandler clientHandler;
    private ClientHandler opponentClientHandler;

    private String playerName;
    private String opponentName;

    private JLabel blackMoveLabel;
    private JLabel whiteMoveLabel;
    private JLabel blackTakeStoneLabel;
    private JLabel whiteTakeStoneLabel;
    private JLabel countLabel;
    private JLabel blackWins;
    private JLabel whiteWins;
    private JPanel panel;

    private JLabel greetingLabel;

    private JComboBox availablePlayers;
    private DefaultComboBoxModel comboBoxModel;
    private String[] playerNames;
    private JButton selectOpponent;
    private SelectOpponentMouseListener selectOpponentMouseListener;



    private JPanel largeHorizontalLine1 ;
    private JPanel largeHorizontalLine2;
    private JPanel largeHorizontalLine3;
    private JPanel largeHorizontalLine4;
    private JPanel mediumHorizontalLine1;
    private JPanel mediumHorizontalLine2;
    private JPanel mediumHorizontalLine3;
    private JPanel mediumHorizontalLine4;
    private JPanel smallHorizontalLine1;
    private JPanel smallHorizontalLine2;
    private JPanel smallHorizontalLine3;
    private JPanel smallHorizontalLine4;
    private JPanel largeVerticalLine1;
    private JPanel largeVerticalLine2;
    private JPanel largeVerticalLine3;
    private JPanel largeVerticalLine4;
    private JPanel mediumVerticalLine1;
    private JPanel mediumVerticalLine2;
    private JPanel mediumVerticalLine3;
    private JPanel mediumVerticalLine4;
    private JPanel smallVerticalLine1;
    private JPanel smallVerticalLine2;
    private JPanel smallVerticalLine3;
    private JPanel smallVerticalLine4;
    private JPanel verticalCrossingLine1;
    private JPanel verticalCrossingLine2;
    private JPanel verticalCrossingLine3;
    private JPanel verticalCrossingLine4;
    private JPanel horizontalCrossingLine1;
    private JPanel horizontalCrossingLine2;
    private JPanel horizontalCrossingLine3;
    private JPanel horizontalCrossingLine4;
    private GameMouseListener myMouseListener1;
    private GameMouseListener myMouseListener2;
    private GameMouseListener myMouseListener3;
    private GameMouseListener myMouseListener4;
    private GameMouseListener myMouseListener5;
    private GameMouseListener myMouseListener6;
    private GameMouseListener myMouseListener7;
    private GameMouseListener myMouseListener8;
    private GameMouseListener myMouseListener9;
    private GameMouseListener myMouseListener10;
    private GameMouseListener myMouseListener11;
    private GameMouseListener myMouseListener12;
    private GameMouseListener myMouseListener13;
    private GameMouseListener myMouseListener14;
    private GameMouseListener myMouseListener15;
    private GameMouseListener myMouseListener16;
    private GameMouseListener myMouseListener17;
    private GameMouseListener myMouseListener18;
    private GameMouseListener myMouseListener19;
    private GameMouseListener myMouseListener20;
    private GameMouseListener myMouseListener21;
    private GameMouseListener myMouseListener22;
    private GameMouseListener myMouseListener23;
    private GameMouseListener myMouseListener24;

    public Frame(String playerName, ClientHandler clientHandler, GameHandler gameHandler){

        this.setTitle("Mühle");
        this.setLayout(null);
        this.setSize(1200,800);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.playerName = playerName;
        logicalColour = true;
        gameOver = false;
        amountOfUnusedWhiteStones = 9;
        amountOfUnusedBlackStones = 9;
        amountOfWhiteStonesOutOfGame = 0;
        amountOfBlackStonesOutOfGame = 0;
        aMillWasCreatedInThePreviousAction = false;
        stoneIsSelected = false;
        selectedStone = null;
        whiteStonesOnBoard = new ArrayList<Feld>();
        blackStonesOnBoard = new ArrayList<Feld>();
        whiteStonesOutOfGame = new ArrayList<Feld>();
        blackStonesOutOfGame = new ArrayList<Feld>();
        changePlayer = false;

        blackMoveLabel = new JLabel("black moves");
        blackMoveLabel.setVerticalAlignment(JLabel.CENTER);
        blackMoveLabel.setHorizontalAlignment(JLabel.CENTER);
        blackMoveLabel.setForeground(Color.white);
        whiteMoveLabel = new JLabel("white moves");
        whiteMoveLabel.setVerticalAlignment(JLabel.CENTER);
        whiteMoveLabel.setHorizontalAlignment(JLabel.CENTER);

        countLabel = new JLabel(String.valueOf(amountOfUnusedBlackStones));
        countLabel.setVerticalAlignment(JLabel.TOP);
        countLabel.setHorizontalAlignment(JLabel.CENTER);
        panel = new JPanel();
        panel.setBackground(new Color(245,245,220,255));
        panel.setBounds(325,325,150,150);
        panel.setLayout(new BorderLayout());
        panel.add(whiteMoveLabel,BorderLayout.CENTER);
        panel.add(countLabel,BorderLayout.NORTH);
        this.add(panel);

        playerNames = new String[Server.getAvailablePlayers().size() - 1];

        int i = 0;
        for(String key : Server.getAvailablePlayers().keySet()) {
            if(!key.equals(playerName)){
                playerNames[i]=key;
                i++;
            }
        }

        //Selecting an opponent
        greetingLabel = new JLabel("Hello, "+playerName+"! Please choose an opponent.");
        greetingLabel.setBounds(775,1,300,50);
        this.add(greetingLabel);
        comboBoxModel = new DefaultComboBoxModel(playerNames);
        availablePlayers = new JComboBox(comboBoxModel);
        availablePlayers.setBounds(775,50,200,30);
        this.add(availablePlayers);

        selectOpponent = new JButton("Select Opponnent");
        selectOpponent.setBounds(1005,50,165,30);

        selectOpponentMouseListener = new SelectOpponentMouseListener(playerName,availablePlayers,gameHandler,clientHandler);
        selectOpponent.addMouseListener(selectOpponentMouseListener);

        this.add(selectOpponent);







        //GUI components of gamefield
        largeHorizontalLine1 = new JPanel();
        largeHorizontalLine1.setBackground(Color.black);
        largeHorizontalLine1.setBounds(100,70,275,10);
        this.add(largeHorizontalLine1);
        largeHorizontalLine2 = new JPanel();
        largeHorizontalLine2.setBackground(Color.black);
        largeHorizontalLine2.setBounds(425,70,275,10);
        this.add(largeHorizontalLine2);
        largeHorizontalLine3 = new JPanel();
        largeHorizontalLine3.setBackground(Color.black);
        largeHorizontalLine3.setBounds(100,720,275,10);
        this.add(largeHorizontalLine3);
        largeHorizontalLine4 = new JPanel();
        largeHorizontalLine4.setBackground(Color.black);
        largeHorizontalLine4.setBounds(425,720,275,10);
        this.add(largeHorizontalLine4);
        mediumHorizontalLine1 = new JPanel();
        mediumHorizontalLine1.setBackground(Color.black);
        mediumHorizontalLine1.setBounds(200,170,175,10);
        this.add(mediumHorizontalLine1);
        mediumHorizontalLine2 = new JPanel();
        mediumHorizontalLine2.setBackground(Color.black);
        mediumHorizontalLine2.setBounds(425,170,175,10);
        this.add(mediumHorizontalLine2);
        mediumHorizontalLine3 = new JPanel();
        mediumHorizontalLine3.setBackground(Color.black);
        mediumHorizontalLine3.setBounds(200,620,175,10);
        this.add(mediumHorizontalLine3);
        mediumHorizontalLine4 = new JPanel();
        mediumHorizontalLine4.setBackground(Color.black);
        mediumHorizontalLine4.setBounds(425,620,175,10);
        this.add(mediumHorizontalLine4);
        smallHorizontalLine1 = new JPanel();
        smallHorizontalLine1.setBackground(Color.black);
        smallHorizontalLine1.setBounds(300,270,75,10);
        this.add(smallHorizontalLine1);
        smallHorizontalLine2 = new JPanel();
        smallHorizontalLine2.setBackground(Color.black);
        smallHorizontalLine2.setBounds(425,270,75,10);
        this.add(smallHorizontalLine2);
        smallHorizontalLine3 = new JPanel();
        smallHorizontalLine3.setBackground(Color.black);
        smallHorizontalLine3.setBounds(300,520,75,10);
        this.add(smallHorizontalLine3);
        smallHorizontalLine4 = new JPanel();
        smallHorizontalLine4.setBackground(Color.black);
        smallHorizontalLine4.setBounds(425,520,75,10);
        this.add(smallHorizontalLine4);
        largeVerticalLine1 = new JPanel();
        largeVerticalLine1.setBackground(Color.black);
        largeVerticalLine1.setBounds(70,100,10,275);
        this.add(largeVerticalLine1);
        largeVerticalLine2 = new JPanel();
        largeVerticalLine2.setBackground(Color.black);
        largeVerticalLine2.setBounds(720,100,10,275);
        this.add(largeVerticalLine2);
        largeVerticalLine3 = new JPanel();
        largeVerticalLine3.setBackground(Color.black);
        largeVerticalLine3.setBounds(720,425,10,275);
        this.add(largeVerticalLine3);
        largeVerticalLine4 = new JPanel();
        largeVerticalLine4.setBackground(Color.black);
        largeVerticalLine4.setBounds(70,425,10,275);
        this.add(largeVerticalLine4);
        mediumVerticalLine1 = new JPanel();
        mediumVerticalLine1.setBackground(Color.black);
        mediumVerticalLine1.setBounds(170,200,10,175);
        this.add(mediumVerticalLine1);
        mediumVerticalLine2 = new JPanel();
        mediumVerticalLine2.setBackground(Color.black);
        mediumVerticalLine2.setBounds(620,200,10,175);
        this.add(mediumVerticalLine2);
        mediumVerticalLine3 = new JPanel();
        mediumVerticalLine3.setBackground(Color.black);
        mediumVerticalLine3.setBounds(620,425,10,175);
        this.add(mediumVerticalLine3);
        mediumVerticalLine4 = new JPanel();
        mediumVerticalLine4.setBackground(Color.black);
        mediumVerticalLine4.setBounds(170,425,10,175);
        this.add(mediumVerticalLine4);
        smallVerticalLine1 = new JPanel();
        smallVerticalLine1.setBackground(Color.black);
        smallVerticalLine1.setBounds(270,300,10,75);
        this.add(smallVerticalLine1);
        smallVerticalLine2 = new JPanel();
        smallVerticalLine2.setBackground(Color.black);
        smallVerticalLine2.setBounds(520,300,10,75);
        this.add(smallVerticalLine2);
        smallVerticalLine3 = new JPanel();
        smallVerticalLine3.setBackground(Color.black);
        smallVerticalLine3.setBounds(270,425,10,75);
        this.add(smallVerticalLine3);
        smallVerticalLine4 = new JPanel();
        smallVerticalLine4.setBackground(Color.black);
        smallVerticalLine4.setBounds(520,425,10,75);
        this.add(smallVerticalLine4);
        verticalCrossingLine1 = new JPanel();
        verticalCrossingLine1.setBackground(Color.black);
        verticalCrossingLine1.setBounds(395,100,10,50);
        this.add(verticalCrossingLine1);
        verticalCrossingLine2 = new JPanel();
        verticalCrossingLine2.setBackground(Color.black);
        verticalCrossingLine2.setBounds(395,200,10,50);
        this.add(verticalCrossingLine2);
        verticalCrossingLine3 = new JPanel();
        verticalCrossingLine3.setBackground(Color.black);
        verticalCrossingLine3.setBounds(395,550,10,50);
        this.add(verticalCrossingLine3);
        verticalCrossingLine4 = new JPanel();
        verticalCrossingLine4.setBackground(Color.black);
        verticalCrossingLine4.setBounds(395,650,10,50);
        this.add(verticalCrossingLine4);
        horizontalCrossingLine1 = new JPanel();
        horizontalCrossingLine1.setBackground(Color.black);
        horizontalCrossingLine1.setBounds(100,395,50,10);
        this.add(horizontalCrossingLine1);
        horizontalCrossingLine2 = new JPanel();
        horizontalCrossingLine2.setBackground(Color.black);
        horizontalCrossingLine2.setBounds(200,395,50,10);
        this.add(horizontalCrossingLine2);
        horizontalCrossingLine3 = new JPanel();
        horizontalCrossingLine3.setBackground(Color.black);
        horizontalCrossingLine3.setBounds(550,395,50,10);
        this.add(horizontalCrossingLine3);
        horizontalCrossingLine4 = new JPanel();
        horizontalCrossingLine4.setBackground(Color.black);
        horizontalCrossingLine4.setBounds(650,395,50,10);
        this.add(horizontalCrossingLine4);


        fields[0][0] = new Feld((byte)1,(byte)1);
        myMouseListener1 = new GameMouseListener(this,fields[0][0]);
        fields[0][0].addMouseListener(myMouseListener1);
        fields[0][0].setBounds(50,50,50,50);
        fields[0][0].setIndex(1);
        this.add(fields[0][0]);

        fields[0][1] = new Feld((byte)1,(byte)2);
        myMouseListener2 = new GameMouseListener(this,fields[0][1]);
        fields[0][1].addMouseListener(myMouseListener2);
        fields[0][1].setBounds(375,50,50,50);
        fields[0][1].setIndex(2);
        this.add(fields[0][1]);

        fields[0][2] = new Feld((byte)1,(byte)3);
        myMouseListener3 = new GameMouseListener(this,fields[0][2]);
        fields[0][2].addMouseListener(myMouseListener3);
        fields[0][2].setBounds(700,50,50,50);
        fields[0][2].setIndex(3);
        this.add(fields[0][2]);

        fields[0][3] = new Feld((byte)1,(byte)4);
        myMouseListener4 = new GameMouseListener(this,fields[0][3]);
        fields[0][3].addMouseListener(myMouseListener4);
        fields[0][3].setBounds(700,375,50,50);
        fields[0][3].setIndex(4);
        this.add(fields[0][3]);

        fields[0][4] = new Feld((byte)1,(byte)5);
        myMouseListener5 = new GameMouseListener(this,fields[0][4]);
        fields[0][4].addMouseListener(myMouseListener5);;
        fields[0][4].setBounds(700,700,50,50);
        fields[0][4].setIndex(5);
        this.add(fields[0][4]);

        fields[0][5] = new Feld((byte)1,(byte)6);
        myMouseListener6 = new GameMouseListener(this,fields[0][5]);
        fields[0][5].addMouseListener(myMouseListener6);
        fields[0][5].setBounds(375,700,50,50);
        fields[0][5].setIndex(6);
        this.add(fields[0][5]);

        fields[0][6] = new Feld((byte)1,(byte)7);
        myMouseListener7 = new GameMouseListener(this,fields[0][6]);
        fields[0][6].addMouseListener(myMouseListener7);
        fields[0][6].setBounds(50,700,50,50);
        fields[0][6].setIndex(7);
        this.add(fields[0][6]);

        fields[0][7] = new Feld((byte)1,(byte)8);
        myMouseListener8 = new GameMouseListener(this,fields[0][7]);
        fields[0][7].addMouseListener(myMouseListener8);
        fields[0][7].setBounds(50,375,50,50);
        fields[0][7].setIndex(8);
        this.add(fields[0][7]);

        fields[1][0] = new Feld((byte)2,(byte)1);
        myMouseListener9 = new GameMouseListener(this,fields[1][0]);
        fields[1][0].addMouseListener(myMouseListener9);
        fields[1][0].setBounds(150,150,50,50);
        fields[1][0].setIndex(9);
        this.add(fields[1][0]);

        fields[1][1] = new Feld((byte)2,(byte)2);
        myMouseListener10 = new GameMouseListener(this,fields[1][1]);
        fields[1][1].addMouseListener(myMouseListener10);
        fields[1][1].setBounds(375,150,50,50);
        fields[1][1].setIndex(10);
        this.add(fields[1][1]);

        fields[1][2] = new Feld((byte)2,(byte)3);
        myMouseListener11 = new GameMouseListener(this,fields[1][2]);
        fields[1][2].addMouseListener(myMouseListener11);
        fields[1][2].setBounds(600,150,50,50);
        fields[1][2].setIndex(11);
        this.add(fields[1][2]);

        fields[1][3] = new Feld((byte)2,(byte)4);
        myMouseListener12 = new GameMouseListener(this,fields[1][3]);
        fields[1][3].addMouseListener(myMouseListener12);
        fields[1][3].setBounds(600,375,50,50);
        fields[1][3].setIndex(12);
        this.add(fields[1][3]);

        fields[1][4] = new Feld((byte)2,(byte)5);
        myMouseListener13 = new GameMouseListener(this,fields[1][4]);
        fields[1][4].addMouseListener(myMouseListener13);
        fields[1][4].setBounds(600,600,50,50);
        fields[1][4].setIndex(13);
        this.add(fields[1][4]);

        fields[1][5] = new Feld((byte)2,(byte)6);
        myMouseListener14 = new GameMouseListener(this,fields[1][5]);
        fields[1][5].addMouseListener(myMouseListener14);
        fields[1][5].setBounds(375,600,50,50);
        fields[1][5].setIndex(14);
        this.add(fields[1][5]);

        fields[1][6] = new Feld((byte)2,(byte)7);
        myMouseListener15 = new GameMouseListener(this,fields[1][6]);
        fields[1][6].addMouseListener(myMouseListener15);
        fields[1][6].setBounds(150,600,50,50);
        fields[1][6].setIndex(15);
        this.add(fields[1][6]);

        fields[1][7] = new Feld((byte)2,(byte)8);
        myMouseListener16 = new GameMouseListener(this,fields[1][7]);
        fields[1][7].addMouseListener(myMouseListener16);
        fields[1][7].setBounds(150,375,50,50);
        fields[1][7].setIndex(16);
        this.add(fields[1][7]);

        fields[2][0] = new Feld((byte)3,(byte)1);
        myMouseListener17 = new GameMouseListener(this,fields[2][0]);
        fields[2][0].addMouseListener(myMouseListener17);
        fields[2][0].setBounds(250,250,50,50);
        fields[2][0].setIndex(17);
        this.add(fields[2][0]);

        fields[2][1] = new Feld((byte)3,(byte)2);
        myMouseListener18 = new GameMouseListener(this,fields[2][1]);
        fields[2][1].addMouseListener(myMouseListener18);
        fields[2][1].setBounds(375,250,50,50);
        fields[2][1].setIndex(18);
        this.add(fields[2][1]);

        fields[2][2] = new Feld((byte)3,(byte)3);
        myMouseListener19 = new GameMouseListener(this,fields[2][2]);
        fields[2][2].addMouseListener(myMouseListener19);
        fields[2][2].setBounds(500,250,50,50);
        fields[2][2].setIndex(19);
        this.add(fields[2][2]);

        fields[2][3] = new Feld((byte)3,(byte)4);
        myMouseListener20 = new GameMouseListener(this,fields[2][3]);
        fields[2][3].addMouseListener(myMouseListener20);
        fields[2][3].setBounds(500,375,50,50);
        fields[2][3].setIndex(20);
        this.add(fields[2][3]);

        fields[2][4] = new Feld((byte)3,(byte)5);
        myMouseListener21 = new GameMouseListener(this,fields[2][4]);
        fields[2][4].addMouseListener(myMouseListener21);
        fields[2][4].setBounds(500,500,50,50);
        fields[2][4].setIndex(21);
        this.add(fields[2][4]);

        fields[2][5] = new Feld((byte)3,(byte)6);
        myMouseListener22 = new GameMouseListener(this,fields[2][5]);
        fields[2][5].addMouseListener(myMouseListener22);
        fields[2][5].setBounds(375,500,50,50);
        fields[2][5].setIndex(22);
        this.add(fields[2][5]);

        fields[2][6] = new Feld((byte)3,(byte)7);
        myMouseListener23 = new GameMouseListener(this,fields[2][6]);
        fields[2][6].addMouseListener(myMouseListener23);
        fields[2][6].setBounds(250,500,50,50);
        fields[2][6].setIndex(23);
        this.add(fields[2][6]);

        fields[2][7] = new Feld((byte)3,(byte)8);
        myMouseListener24 = new GameMouseListener(this,fields[2][7]);
        fields[2][7].addMouseListener(myMouseListener24);
        fields[2][7].setBounds(250,375,50,50);
        fields[2][7].setIndex(24);
        this.add(fields[2][7]);
        JOptionPane jOptionPane = new JOptionPane();

        this.add(jOptionPane);


        JLabel TimerLabelWhite = new JLabel();
        JLabel TimerLabelBlack = new JLabel();
        TimerLabelWhite.setBounds(200,1,200,50);
        TimerLabelBlack.setBounds(450,1,200,50);
        this.add(TimerLabelWhite);
        this.add(TimerLabelBlack);
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 120;
            int j = 120;

            public void run() {
                if (playerColour) {
                    if (itsYourTurn) {
                        TimerLabelWhite.setText("White Player Time left: " + i);
                        i--;
                        TimerLabelBlack.setText(" Black Player Time left: " + j);


                    } else {
                        TimerLabelWhite.setText(" White Player Time left: " + i);
                        TimerLabelBlack.setText("Black Player Time left: " + j);
                        j--;
                    }
                }else{
                    if (itsYourTurn) {
                        TimerLabelBlack.setText(" Black Player Time left: " + j);
                        j--;
                        TimerLabelWhite.setText(" White Player Time left: " + i);


                    } else {
                        TimerLabelWhite.setText("White Player Time left: " + i);
                        TimerLabelBlack.setText("Black Player Time left: " + j);
                        i--;
                    }
                }
                if(i<0 || j<0){
                    isGameOver();
                }

            }
        }, 0, 1000);

        this.setVisible(true);
    }



    public GameHandler getOpponentGameHandler() {
        return opponentGameHandler;
    }
    public boolean isChangePlayer() {
        return changePlayer;
    }
    public void setChangePlayer(boolean changePlayer) {
        this.changePlayer = changePlayer;
    }
    public boolean isItsYourTurn() {
        return itsYourTurn;
    }
    public void setItsYourTurn(boolean itsYourTurn) {
        this.itsYourTurn = itsYourTurn;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public boolean isaMillWasCreatedInThePreviousAction() {
        return aMillWasCreatedInThePreviousAction;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public boolean isPlayerColour() {
        return logicalColour;
    }
    public void setaMillWasCreatedInThePreviousAction(boolean aMillWasCreatedInThePreviousAction) {
        this.aMillWasCreatedInThePreviousAction = aMillWasCreatedInThePreviousAction;
    }
    public int getAmountOfUnusedWhiteStones() {
        return amountOfUnusedWhiteStones;
    }
    public int getAmountOfUnusedBlackStones() {
        return amountOfUnusedBlackStones;
    }

    public int getAmountOfUnusedStones(boolean playerColour){
        if (playerColour){return getAmountOfUnusedWhiteStones();}
        else {return getAmountOfUnusedBlackStones();}
    }
    public void firstPhaseMove(Feld field){
        if (field.isEmpty()){
            firstPhaseSetStone(field);
            if (stoneIsInMill(field)){
                addRequestToDeleteStone();
                setaMillWasCreatedInThePreviousAction(true);
            }
            else {
                changePlayer = true;
                adaptDisplayOfCurrentPlayer();
                opponentGameHandler.getFrame().adaptDisplayOfCurrentPlayer();
            }
        }
    }
    public void firstPhaseSetStone(Feld field){
        Feld opponentsField = opponentGameHandler.getFrame().indexToField(field.getIndex());
        if (logicalColour){
            field.setIcon(new ImageIcon("WeisserStein.png"));
            opponentsField.setIcon(new ImageIcon("WeisserStein.png"));

            whiteStonesOnBoard.add(field);
            opponentGameHandler.getFrame().whiteStonesOnBoard.add(opponentsField);

            amountOfUnusedWhiteStones --;
            opponentGameHandler.getFrame().amountOfUnusedWhiteStones--;
        }
        else {
            field.setIcon(new ImageIcon("SchwarzerStein.png"));
            opponentsField.setIcon(new ImageIcon("SchwarzerStein.png"));

            blackStonesOnBoard.add(field);
            opponentGameHandler.getFrame().blackStonesOnBoard.add(opponentsField);

            amountOfUnusedBlackStones --;
            opponentGameHandler.getFrame().amountOfUnusedBlackStones--;
        }
        field.setEmpty(false);
        opponentsField.setEmpty(false);

        field.setColourOfStone(logicalColour);
        opponentsField.setColourOfStone(logicalColour);

        logicalColour = !logicalColour;
        opponentGameHandler.getFrame().logicalColour = ! opponentGameHandler.getFrame().logicalColour;
    }
    public void secondPhaseMove(Feld field){
        if (!logicalColour){
            panel.remove(countLabel);
            opponentGameHandler.getFrame().panel.remove(opponentGameHandler.getFrame().countLabel);
        }
        if (stoneIsSelected){
            if (fieldsAreNeighbours(selectedStone,field) && field.isEmpty()){
                secondPhaseSetStone(field);
                if (stoneIsInMill(field)){
                    addRequestToDeleteStone();
                    setaMillWasCreatedInThePreviousAction(true);
                }
                else {
                    adaptDisplayOfCurrentPlayer();
                    opponentGameHandler.getFrame().adaptDisplayOfCurrentPlayer();
                    changePlayer = true;
                }
            }
            else {
                selectAStoneYouWantToMoveInPhaseTwo(field);
            }
        }
        else {
            if (allStonesAreBlocked()){
                gameOver = true;
                opponentGameHandler.getFrame().gameOver = true;
            }
            else {
                selectAStoneYouWantToMoveInPhaseTwo(field);
            }
        }
    }

    public void secondPhaseSetStone(Feld field){
        Feld opponentsField = opponentGameHandler.getFrame().indexToField(field.getIndex());

        field.setEmpty(false);
        opponentsField.setEmpty(false);

        selectedStone.setEmpty(true);
        opponentGameHandler.getFrame().selectedStone.setEmpty(true);

        selectedStone.setBackground(Color.white);
        opponentGameHandler.getFrame().selectedStone.setBackground(Color.white);
        selectedStone.setIcon(null);
        opponentGameHandler.getFrame().selectedStone.setIcon(null);
        stoneIsSelected = false;
        opponentGameHandler.getFrame().stoneIsSelected = false;
        if (logicalColour){
            field.setColourOfStone(true);
            opponentsField.setColourOfStone(true);
            field.setIcon(new ImageIcon("WeisserStein.png"));
            opponentsField.setIcon(new ImageIcon("WeisserStein.png"));
        }
        else {
            field.setColourOfStone(false);
            opponentsField.setColourOfStone(false);

            field.setIcon(new ImageIcon("SchwarzerStein.png"));
            opponentsField.setIcon(new ImageIcon("SchwarzerStein.png"));
        }
        logicalColour = !logicalColour;
        opponentGameHandler.getFrame().logicalColour = !opponentGameHandler.getFrame().logicalColour;
    }
    public void selectAStoneYouWantToMoveInPhaseTwo(Feld field){
        Feld opponentsField = opponentGameHandler.getFrame().indexToField(field.getIndex());
        if (!field.isEmpty() && field.isColourOfStone() == logicalColour){
            if (!stoneIsBlocked(field)){
                if (stoneIsSelected){
                    selectedStone.setBackground(Color.white);
                    opponentGameHandler.getFrame().selectedStone.setBackground(Color.white);
                }
                field.setBackground(Color.green);
                selectedStone = field;
                opponentGameHandler.getFrame().selectedStone = opponentsField;

                stoneIsSelected = true;
                opponentGameHandler.getFrame().stoneIsSelected = true;
            }
            else {
                if (stoneIsSelected){
                    selectedStone.setBackground(Color.white);
                }
                field.setBackground(Color.red);
                selectedStone = field;
                opponentGameHandler.getFrame().selectedStone = opponentsField;
                stoneIsSelected = true;
                opponentGameHandler.getFrame().stoneIsSelected = true;
            }
        }
    }
    public void selectAStoneYouWantToMoveInPhaseThree(Feld field){
        Feld opponentsField = opponentGameHandler.getFrame().indexToField(field.getIndex());
        if (!field.isEmpty() && field.isColourOfStone() == logicalColour){
            if (stoneIsSelected){
                selectedStone.setBackground(Color.white);
            }
            field.setBackground(Color.green);
            selectedStone = field;
            opponentGameHandler.getFrame().selectedStone = opponentsField;

            stoneIsSelected = true;
            opponentGameHandler.getFrame().stoneIsSelected = true;
        }
    }
    public void thirdPhaseMove(Feld field){
        if (stoneIsSelected){
            if (field.isEmpty()){
                secondPhaseSetStone(field);
                if (stoneIsInMill(field)){
                    addRequestToDeleteStone();
                    aMillWasCreatedInThePreviousAction = true;
                }
                else {
                    adaptDisplayOfCurrentPlayer();
                    opponentGameHandler.getFrame().adaptDisplayOfCurrentPlayer();
                    changePlayer = true;
                }
            }
            else {
                selectAStoneYouWantToMoveInPhaseThree(field);
            }
        }
        else {
            selectAStoneYouWantToMoveInPhaseThree(field);
        }
    }

    public boolean fieldsAreNeighbours(Feld selectedStone, Feld field){
        boolean returnValue = false;
        if (selectedStone.getCircle() == (byte)1){
            switch (selectedStone.getPositionOnCircle()) {
                case 1 -> {
                    if (field.getCircle() == 1 && (field.getPositionOnCircle() == 8 || field.getPositionOnCircle() == 2)) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if ((field.getCircle() == 1 && Math.abs(field.getPositionOnCircle() - selectedStone.getPositionOnCircle()) == 1) || (field.getPositionOnCircle() == selectedStone.getPositionOnCircle() && field.getCircle() == 2)) {returnValue = true;}
                    break;
                }
                case 3,5,7 -> {
                    if (field.getCircle() == 1 && Math.abs(field.getPositionOnCircle() - selectedStone.getPositionOnCircle()) == 1) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if ((field.getCircle() == 1 && (field.getPositionOnCircle() == 1 || field.getPositionOnCircle() == 7)) || (field.getPositionOnCircle() == 8 && field.getCircle() == 2)) {returnValue = true;}
                    break;
                }
            }
        }
        else if (selectedStone.getCircle() == (byte)2){
            switch (selectedStone.getPositionOnCircle()) {
                case 1 -> {
                    if (field.getCircle() == 2 && (field.getPositionOnCircle() == 8 || field.getPositionOnCircle() == 2)) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if ((field.getCircle() == 2 && Math.abs(field.getPositionOnCircle() - selectedStone.getPositionOnCircle()) == 1) || (field.getPositionOnCircle() == selectedStone.getPositionOnCircle() && (field.getCircle() == 1 || field.getCircle() == 3))) {returnValue = true;}
                    break;
                }
                case 3,5,7 -> {
                    if (field.getCircle() == 2 && Math.abs(field.getPositionOnCircle() - selectedStone.getPositionOnCircle()) == 1) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if ((field.getCircle() == 2 && (field.getPositionOnCircle() == 1 || field.getPositionOnCircle() == 7)) || (field.getPositionOnCircle() == 8 && (field.getCircle() == 1 || field.getCircle() == 3))) {returnValue = true;}
                    break;
                }
            }
        }
        else {
            switch (selectedStone.getPositionOnCircle()) {
                case 1 -> {
                    if (field.getCircle() == 3 && (field.getPositionOnCircle() == 8 || field.getPositionOnCircle() == 2)) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if ((field.getCircle() == 3 && Math.abs(field.getPositionOnCircle() - selectedStone.getPositionOnCircle()) == 1) || (field.getPositionOnCircle() == selectedStone.getPositionOnCircle() && field.getCircle() == 2)) {returnValue = true;}
                    break;
                }
                case 3,5,7 -> {
                    if (field.getCircle() == 3 && Math.abs(field.getPositionOnCircle() - selectedStone.getPositionOnCircle()) == 1) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if ((field.getCircle() == 3 && (field.getPositionOnCircle() == 1 || field.getPositionOnCircle() == 7)) || (field.getPositionOnCircle() == 8 && field.getCircle() == 2)) {returnValue = true;}
                    break;
                }
            }
        }
        return returnValue;
    }
    public boolean checkLineOnCircleForMill (Feld field, int circle, int positionOnCircle1, int positionOnCircle2){
        if (!(fields[(circle - 1)][(positionOnCircle1 - 1)].isEmpty() || fields[(circle - 1)][(positionOnCircle2 - 1)].isEmpty()) && ((fields[(circle - 1)][(positionOnCircle1 - 1)].isColourOfStone() == field.isColourOfStone()) && (field.isColourOfStone() == fields[(circle - 1)][(positionOnCircle2 - 1)].isColourOfStone()))){
            return true;
        }
        return false;
    }
    public boolean checkLineBetweenCirclesForMill (Feld field, int positionOnCircle,int circle1,int circle2){
        if (!(fields[(circle1 - 1)][(positionOnCircle - 1)].isEmpty() || fields[(circle2 - 1)][(positionOnCircle - 1)].isEmpty()) && ((fields[(circle1 - 1)][(positionOnCircle - 1)].isColourOfStone() == field.isColourOfStone()) && (field.isColourOfStone() == fields[(circle2 - 1)][(positionOnCircle - 1)].isColourOfStone()))){
            return true;
        }
        return false;
    }

    public boolean stoneIsInMill(Feld field){
        boolean returnValue = false;
        if (field.getCircle() == (byte)1){
            switch (field.getPositionOnCircle()) {
                case 1 -> {
                    if (checkLineOnCircleForMill(field, 1, 2, 3)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 1, 7, 8)) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if (checkLineOnCircleForMill(field, 1, field.getPositionOnCircle() - 1, field.getPositionOnCircle() + 1)) {returnValue = true;}
                    else if (checkLineBetweenCirclesForMill(field, field.getPositionOnCircle(), 2, 3)) {returnValue = true;}
                    break;
                }
                case 3,5 -> {
                    if (checkLineOnCircleForMill(field, 1, field.getPositionOnCircle()-2, field.getPositionOnCircle()-1)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 1, field.getPositionOnCircle()+1, field.getPositionOnCircle()+2)) {returnValue = true;}
                    break;
                }
                case 7 -> {
                    if (checkLineOnCircleForMill(field, 1, 5, 6)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 1, 8, 1)) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if (checkLineOnCircleForMill(field, 1, 1, 7)) {returnValue = true;}
                    else if (checkLineBetweenCirclesForMill(field, 8, 2, 3)) {returnValue = true;}
                    break;
                }
            }
        }
        else if (field.getCircle() == (byte)2){
            switch (field.getPositionOnCircle()) {
                case 1 -> {
                    if (checkLineOnCircleForMill(field, 2, 2, 3)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 2, 7, 8)) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if (checkLineOnCircleForMill(field, 2, field.getPositionOnCircle() - 1, field.getPositionOnCircle() + 1)) {returnValue = true;}
                    else if (checkLineBetweenCirclesForMill(field, field.getPositionOnCircle(), 1, 3)) {returnValue = true;}
                    break;
                }
                case 3,5 -> {
                    if (checkLineOnCircleForMill(field, 2, field.getPositionOnCircle()-2, field.getPositionOnCircle()-1)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 2, field.getPositionOnCircle()+1, field.getPositionOnCircle()+2)) {returnValue = true;}
                }
                case 7 -> {
                    if (checkLineOnCircleForMill(field, 2, 5, 6)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 2, 8, 1)) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if (checkLineOnCircleForMill(field, 2, 1, 7)) {returnValue = true;}
                    else if (checkLineBetweenCirclesForMill(field, 8, 1, 3)) {returnValue = true;}
                    break;
                }
            }
        }
        else {
            switch (field.getPositionOnCircle()) {
                case 1 -> {
                    if (checkLineOnCircleForMill(field, 3, 2, 3)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 3, 7, 8)) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if (checkLineOnCircleForMill(field, 3, field.getPositionOnCircle() - 1, field.getPositionOnCircle() + 1)) {returnValue = true;}
                    else if (checkLineBetweenCirclesForMill(field, field.getPositionOnCircle(), 1, 2)) {returnValue = true;}
                    break;
                }
                case 3,5 -> {
                    if (checkLineOnCircleForMill(field, 3, field.getPositionOnCircle()-2, field.getPositionOnCircle()-1)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 3, field.getPositionOnCircle()+1, field.getPositionOnCircle()+2)) {returnValue = true;}
                }
                case 7 -> {
                    if (checkLineOnCircleForMill(field, 3, 5, 6)) {returnValue = true;}
                    else if (checkLineOnCircleForMill(field, 3, 8, 1)) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if (checkLineOnCircleForMill(field, 3, 1, 7)) {returnValue = true;}
                    else if (checkLineBetweenCirclesForMill(field, 8, 1, 2)) {returnValue = true;}
                    break;
                }
            }
        }
        return returnValue;
    }
    public boolean stoneIsBlocked(Feld field){
        boolean returnValue = false;
        if (field.getCircle() == (byte)1){
            switch (field.getPositionOnCircle()) {
                case 1 -> {
                    if (!fields[0][7].isEmpty() && !fields[0][1].isEmpty()) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if (!fields[0][field.getPositionOnCircle() - 2].isEmpty() && !fields[0][field.getPositionOnCircle()].isEmpty() && !fields[1][field.getPositionOnCircle() - 1].isEmpty()) {returnValue = true;}
                    break;
                }
                case 3,5,7 -> {
                    if (!fields[0][field.getPositionOnCircle() - 2].isEmpty() && !fields[0][field.getPositionOnCircle()].isEmpty()) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if (!fields[0][6].isEmpty() && !fields[0][0].isEmpty() && !fields[1][7].isEmpty()) {returnValue = true;}
                    break;
                }
            }
        }
        else if (field.getCircle() == (byte)2){
            switch (field.getPositionOnCircle()) {
                case 1 -> {
                    if (!fields[1][7].isEmpty() && !fields[1][1].isEmpty()) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if (!fields[1][field.getPositionOnCircle() - 2].isEmpty() && !fields[1][field.getPositionOnCircle()].isEmpty() && !fields[0][field.getPositionOnCircle() - 1].isEmpty() && !fields[2][field.getPositionOnCircle() - 1].isEmpty()) {returnValue = true;}
                    break;
                }
                case 3,5,7 -> {
                    if (!fields[1][field.getPositionOnCircle() - 2].isEmpty() && !fields[1][field.getPositionOnCircle()].isEmpty()) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if (!fields[1][6].isEmpty() && !fields[1][0].isEmpty() && !fields[0][7].isEmpty() && !fields[2][7].isEmpty()) {returnValue = true;}
                    break;
                }
            }
        }
        else {
            switch (field.getPositionOnCircle()) {
                case 1 -> {
                    if (!fields[2][7].isEmpty() && !fields[2][1].isEmpty()) {returnValue = true;}
                    break;
                }
                case 2,4,6 -> {
                    if (!fields[2][field.getPositionOnCircle() - 2].isEmpty() && !fields[2][field.getPositionOnCircle()].isEmpty() && !fields[1][field.getPositionOnCircle() - 1].isEmpty()) {returnValue = true;}
                    break;
                }
                case 3,5,7 -> {
                    if (!fields[2][field.getPositionOnCircle() - 2].isEmpty() && !fields[2][field.getPositionOnCircle()].isEmpty()) {returnValue = true;}
                    break;
                }
                case 8 -> {
                    if (!fields[2][6].isEmpty() && !fields[2][0].isEmpty() && !fields[1][7].isEmpty()) {returnValue = true;}
                    break;
                }
            }
        }
        return returnValue;
    }
    public boolean allStonesAreBlocked(){
        boolean returnValue = false;
        if (logicalColour){
            int i = 0;
            while (i < whiteStonesOnBoard.size() && stoneIsBlocked(whiteStonesOnBoard.get(i))) {
                i++;
            }
            if (i == whiteStonesOnBoard.size()){
                i = 0;
                while (i < whiteStonesOnBoard.size()) {
                    whiteStonesOnBoard.get(i).setBackground(Color.red);
                    i++;
                }
                returnValue = true;
            }
        }
        else {
            int i = 0;
            while (i < blackStonesOnBoard.size() && stoneIsBlocked(blackStonesOnBoard.get(i))) {
                i++;
            }
            if (i == blackStonesOnBoard.size()){
                i = 0;
                while (i < blackStonesOnBoard.size()) {
                    blackStonesOnBoard.get(i).setBackground(Color.red);
                    i++;
                }
                returnValue = true;
            }
        }
        return returnValue;
    }
    public void removeStone(Feld field){
        Feld opponentsField = opponentGameHandler.getFrame().indexToField(field.getIndex());
        if (!field.isEmpty() && field.isColourOfStone() == logicalColour){
            if (notAllStonesAreInMill()){
                if (!(stoneIsInMill(field))){
                    if (logicalColour){

                        whiteStonesOutOfGame.add(field);
                        opponentGameHandler.getFrame().whiteStonesOnBoard.add(opponentsField);

                        whiteStonesOnBoard.remove(field);
                        opponentGameHandler.getFrame().whiteStonesOnBoard.remove(opponentsField);

                        amountOfWhiteStonesOutOfGame++;
                        opponentGameHandler.getFrame().amountOfWhiteStonesOutOfGame++;
                    }
                    else {

                        blackStonesOutOfGame.add(field);
                        opponentGameHandler.getFrame().blackStonesOnBoard.add(opponentsField);

                        blackStonesOnBoard.remove(field);
                        opponentGameHandler.getFrame().blackStonesOnBoard.remove(opponentsField);

                        amountOfBlackStonesOutOfGame++;
                        opponentGameHandler.getFrame().amountOfBlackStonesOutOfGame++;
                    }
                    field.setIcon(null);
                    opponentsField.setIcon(null);

                    removeRequestToDeleteStone();

                    adaptDisplayOfCurrentPlayer();
                    opponentGameHandler.getFrame().adaptDisplayOfCurrentPlayer();

                    field.setEmpty(true);
                    opponentsField.setEmpty(true);

                    setaMillWasCreatedInThePreviousAction(false);
                    changePlayer = true;
                }
            }
            else {
                if (logicalColour){
                    whiteStonesOnBoard.remove(field);
                    opponentGameHandler.getFrame().whiteStonesOnBoard.remove(opponentsField);

                    whiteStonesOutOfGame.add(field);
                    opponentGameHandler.getFrame().whiteStonesOnBoard.remove(opponentsField);

                    amountOfWhiteStonesOutOfGame++;
                    opponentGameHandler.getFrame().amountOfWhiteStonesOutOfGame++;
                }
                else {
                    blackStonesOnBoard.remove(field);
                    opponentGameHandler.getFrame().blackStonesOnBoard.remove(opponentsField);

                    blackStonesOutOfGame.add(field);
                    opponentGameHandler.getFrame().blackStonesOutOfGame.add(opponentsField);

                    amountOfBlackStonesOutOfGame++;
                    opponentGameHandler.getFrame().amountOfBlackStonesOutOfGame++;
                }
                field.setIcon(null);
                opponentsField.setIcon(null);
                removeRequestToDeleteStone();

                adaptDisplayOfCurrentPlayer();
                opponentGameHandler.getFrame().adaptDisplayOfCurrentPlayer();

                field.setEmpty(true);
                opponentsField.setEmpty(true);

                setaMillWasCreatedInThePreviousAction(false);
                changePlayer = true;
            }
        }
    }
    public boolean notAllStonesAreInMill(){
        boolean returnValue = true;
        if (logicalColour){
            int i = 0;
            while (i < whiteStonesOnBoard.size() && stoneIsInMill(whiteStonesOnBoard.get(i))) {
                i++;
            }
            if (i >= whiteStonesOnBoard.size()){
                returnValue = false;
            }
        }
        else{
            int i = 0;
            while (i < blackStonesOnBoard.size() && stoneIsInMill(blackStonesOnBoard.get(i))) {
                i++;
            }
            if (i >= blackStonesOnBoard.size()){
                returnValue = false;
            }
        }
        return returnValue;
    }
    public void adaptDisplayOfCurrentPlayer(){
        if (logicalColour){
            panel.remove(blackMoveLabel);
            panel.setBackground(new Color(245,245,220,255));
            blackMoveLabel.setForeground(Color.black);
            countLabel.setForeground(Color.black);
            countLabel.setText(String.valueOf(amountOfUnusedBlackStones));
            panel.add(whiteMoveLabel);
        }
        else {
            panel.remove(whiteMoveLabel);
            panel.setBackground(Color.black);
            blackMoveLabel.setForeground(Color.white);
            countLabel.setForeground(Color.white);
            panel.add(blackMoveLabel);
        }
    }
    public void addRequestToDeleteStone(){
        if(logicalColour){
            blackTakeStoneLabel = new JLabel("take white stone");
            blackTakeStoneLabel.setVerticalAlignment(JLabel.BOTTOM);
            blackTakeStoneLabel.setHorizontalAlignment(JLabel.CENTER);
            blackTakeStoneLabel.setForeground(Color.white);
            panel.add(blackTakeStoneLabel);
        }
        else {
            whiteTakeStoneLabel = new JLabel("take black stone");
            whiteTakeStoneLabel.setVerticalAlignment(JLabel.BOTTOM);
            whiteTakeStoneLabel.setHorizontalAlignment(JLabel.CENTER);
            panel.add(whiteTakeStoneLabel);
        }
    }
    public void removeRequestToDeleteStone(){
        if(logicalColour){
            panel.remove(blackTakeStoneLabel);
        }
        else {
            panel.remove(whiteTakeStoneLabel);
        }
    }
    public void displayWinner(Boolean playerColour){
        if (playerColour){
            panel.remove(whiteMoveLabel);
            panel.setBackground(Color.white);
            blackWins = new JLabel("Black Wins");
            blackWins.setVerticalAlignment(JLabel.CENTER);
            blackWins.setHorizontalAlignment(JLabel.CENTER);
            panel.add(blackWins);
        }
        else {
            panel.remove(blackMoveLabel);
            panel.setBackground(Color.white);
            whiteWins = new JLabel("White Wins");
            whiteWins.setVerticalAlignment(JLabel.CENTER);
            whiteWins.setHorizontalAlignment(JLabel.CENTER);
            panel.add(whiteWins);
        }
    }
    public boolean lessThanThreeStonesOnBoard(Boolean playerColour){
        if (playerColour){
            if (whiteStonesOutOfGame.size() > 6){
                return true;
            }
        }
        else {
            if (blackStonesOutOfGame.size() > 6){
                return true;
            }
        }
        return false;
    }
    public int getAmountOfStonesOnBoard (boolean playerColour){
        if(playerColour){
            return whiteStonesOnBoard.size();
        }
        else {
            return blackStonesOnBoard.size();
        }
    }
    public int getAmountOfStonesOutOfGame (boolean playerColour){
        if(playerColour){
            return amountOfWhiteStonesOutOfGame;
        }
        else {
            return amountOfBlackStonesOutOfGame;
        }
    }
    public Feld indexToField(int index) {
        Feld returnValue = new Feld((byte)0,(byte) 0);
        switch (index) {
            case 1: returnValue = fields[0][0];
                break;
            case 2: returnValue = fields[0][1];
                break;
            case 3: returnValue = fields[0][2];
                break;
            case 4: returnValue = fields[0][3];
                break;
            case 5: returnValue = fields[0][4];
                break;
            case 6: returnValue = fields[0][5];
                break;
            case 7: returnValue = fields[0][6];
                break;
            case 8: returnValue = fields[0][7];
                break;
            case 9: returnValue = fields[1][0];
                break;
            case 10: returnValue = fields[1][1];
                break;
            case 11: returnValue = fields[1][2];
                break;
            case 12: returnValue = fields[1][3];
                break;
            case 13: returnValue = fields[1][4];
                break;
            case 14: returnValue = fields[1][5];
                break;
            case 15: returnValue = fields[1][6];
                break;
            case 16: returnValue = fields[1][7];
                break;
            case 17: returnValue = fields[2][0];
                break;
            case 18: returnValue = fields[2][1];
                break;
            case 19: returnValue = fields[2][2];
                break;
            case 20: returnValue = fields[2][3];
                break;
            case 21: returnValue = fields[2][4];
                break;
            case 22: returnValue = fields[2][5];
                break;
            case 23: returnValue = fields[2][6];
                break;
            case 24: returnValue = fields[2][7];
                break;
        }
        return returnValue;
    }

}
