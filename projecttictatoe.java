import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class projecttictatoe {
    int boardWidth = 950;
    int boardHeight = 800; 

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JLabel scoreLabelX = new JLabel();
    JLabel scoreLabelO = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel scorePanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX; 

    

    int scoreX = 0;
    int scoreO = 0;

    boolean gameOver = false;
    int turns = 0;

    projecttictatoe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(30,20));
        


        textLabel.setBackground(new Color(0,48,79));
        textLabel.setForeground(Color.black);
        textLabel.setFont(new Font("Cooper black", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);
        textLabel.setBorder(new LineBorder(new Color(14,16,15),5));
        textLabel.setBorder(new LineBorder(new Color(14,16,15),5));


        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.YELLOW);
        frame.add(boardPanel);

        scorePanel.setLayout(new GridLayout(1, 2));
        scoreLabelX.setBackground(Color.RED);
        scoreLabelX.setForeground(Color.white);
        scoreLabelX.setFont(new Font("Arial", Font.BOLD, 30));
        scoreLabelX.setHorizontalAlignment(JLabel.CENTER);
        scoreLabelX.setText("Score X: " + scoreX);
        scoreLabelX.setOpaque(true);
        scorePanel.add(scoreLabelX);
        scoreLabelX.setBorder(new LineBorder(new Color(14,16,15),5));

        scoreLabelO.setBackground(Color.RED);
        scoreLabelO.setForeground(Color.white);
        scoreLabelO.setFont(new Font("Arial", Font.BOLD, 30));
        scoreLabelO.setHorizontalAlignment(JLabel.CENTER);
        scoreLabelO.setText("Score O: " + scoreO);
        scoreLabelO.setOpaque(true);
        scorePanel.add(scoreLabelO);
        scoreLabelO.setBorder(new LineBorder(new Color(14,16,15),5));

        frame.add(scorePanel, BorderLayout.SOUTH);
        
    {
        

        JButton resetScoreButton = new JButton("Reset Score");
       
        resetScoreButton.setFont(new Font("Cooper black", Font.BOLD, 20));
        resetScoreButton.setBackground(new Color(188,108,37));
        resetScoreButton.setBorder(new LineBorder(new Color(14,16,15),5));
        
        resetScoreButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                resetScore();
            }
        
    });
        scorePanel.add(resetScoreButton);
}

        // Exit and Reset Button
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.setBorder(new LineBorder(new Color(14,16,15),5));

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Cooper black", Font.BOLD,40));

        resetButton.setBackground(new Color(96,108,56));
        resetButton.setForeground(Color.black);
        resetButton.setBorder(new LineBorder(new Color(14,16,15),5));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Cooper black", Font.BOLD,40));
        exitButton.setBackground(new Color(52,78,68));
        exitButton.setForeground(Color.white);
        exitButton.setBorder(new LineBorder(new Color(14,16,15),5));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);
        frame.add(buttonPanel, BorderLayout.EAST);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(new Color(0,180,216));
                tile.setForeground(Color.black);
                tile.setFont(new Font("Cooper black", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setBorder(new LineBorder(Color.BLACK,5));

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            if(currentPlayer=="X")
                            {
                                tile.setForeground(Color.white);
                            }
                            else if(currentPlayer=="O")
                            {
                                tile.setForeground(new Color(20,25,29));
                            }
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }

                    }
                });
            }
        }
    }

    void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().equals("")) continue;
            
            if (board[r][0].getText().equals(board[r][1].getText()) &&
                board[r][1].getText().equals(board[r][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals("")) continue;

            if (board[0][c].getText().equals(board[1][c].getText()) &&
                board[1][c].getText().equals(board[2][c].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }
        if (board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()) &&
            !board[0][0].getText().equals("")) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        if (board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()) &&
            !board[0][2].getText().equals("")) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }


if (turns == 9) {
            for (int r = 0; r <3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }
    void setWinner(JButton tile) {
        tile.setForeground(Color.blue);
        tile.setBackground(new Color(132,117,119));
        if (currentPlayer.equals(playerX)) {
            scoreX++;
            scoreLabelX.setText("Score X: " + (scoreX/3));
        } else {
            scoreO++;
            scoreLabelO.setText("Score O: " + (scoreO/3));
        }
        textLabel.setText(currentPlayer + " is the winner!");
    }
    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
    void resetGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(new Color(164,22,26));
                board[r][c].setForeground(Color.white);
            }
        }
        currentPlayer = playerX;
        gameOver = false;
        turns = 0;
        textLabel.setText("Tic-Tac-Toe");
    }
    void resetScore() {
        scoreX = 0;
        scoreO = 0;
        scoreLabelX.setText("Score X: " + scoreX);
        scoreLabelO.setText("Score O: " + scoreO);
    }

    public static void main(String[] args) {
        new projecttictatoe();
    }
}

