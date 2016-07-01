import java.util.ArrayList;

public class PegSolver
{
   private char[][] board = { { ' ', ' ', '•', '•', '•', ' ', ' ' },
         { ' ', ' ', '•', '•', '•', ' ', ' ' },
         { '•', '•', '•', '•', '•', '•', '•' },
         { '•', '•', '•', 'o', '•', '•', '•' },
         { '•', '•', '•', '•', '•', '•', '•' },
         { ' ', ' ', '•', '•', '•', ' ', ' ' },
         { ' ', ' ', '•', '•', '•', ' ', ' ' } };
   private ArrayList<String> solving;
   boolean solved;

   public PegSolver()
   {
      solving = new ArrayList<>();
      solved = false;
   }

   public void solve()
   {
      if (solved)
         return;
      if (isSolved())
      {
         printSolution();
         solved = true;
         return;
      }
      ArrayList<String> moves = getPossibleMoves();
      for (String move : moves)
      {
         solving.add(move);
         move(move);
         solve();
         moveBack(move);
         solving.remove(solving.size() - 1);
      }
   }

   public void printSolution()
   {
      for (String s : solving)
      {
         System.out.println(s);
      }
   }

   private ArrayList<String> getPossibleMoves()
   {
      ArrayList<String> list = new ArrayList<>();
      for (int i = 0; i < board.length; i++)
         for (int j = 0; j < board[0].length; j++)
         {
            try
            {
               if (board[i][j] == '•' && board[i][j - 1] == '•'
                     && board[i][j - 2] == 'o')
                  list.add(i + "," + j + ",left");
            }
            catch (Exception e)
            {
            }

            try
            {
               if (board[i][j] == '•' && board[i - 1][j] == '•'
                     && board[i - 2][j] == 'o')
                  list.add(i + "," + j + ",up");
            }
            catch (Exception e)
            {
            }

            try
            {
               if (board[i][j] == '•' && board[i + 1][j] == '•'
                     && board[i + 2][j] == 'o')
                  list.add(i + "," + j + ",down");
            }
            catch (Exception e)
            {
            }

            try
            {
               if (board[i][j] == '•' && board[i][j + 1] == '•'
                     && board[i][j + 2] == 'o')
                  list.add(i + "," + j + ",right");
            }
            catch (Exception e)
            {
            }
         }
      return list;
   }

   private void move(String command)
   {
      String[] token = command.split(",");
      int row = Integer.parseInt(token[0]);
      int column = Integer.parseInt(token[1]);
      board[row][column] = 'o';
      switch (token[2])
      {
         case "up":
            board[row - 1][column] = 'o';
            board[row - 2][column] = '•';
            break;
         case "down":
            board[row + 1][column] = 'o';
            board[row + 2][column] = '•';
            break;
         case "left":
            board[row][column - 1] = 'o';
            board[row][column - 2] = '•';
            break;
         case "right":
            board[row][column + 1] = 'o';
            board[row][column + 2] = '•';
            break;
      }
   }

   private void moveBack(String command)
   {
      String[] token = command.split(",");
      int row = Integer.parseInt(token[0]);
      int column = Integer.parseInt(token[1]);
      board[row][column] = '•';
      switch (token[2])
      {
         case "up":
            board[row - 1][column] = '•';
            board[row - 2][column] = 'o';
            break;
         case "down":
            board[row + 1][column] = '•';
            board[row + 2][column] = 'o';
            break;
         case "left":
            board[row][column - 1] = '•';
            board[row][column - 2] = 'o';
            break;
         case "right":
            board[row][column + 1] = '•';
            board[row][column + 2] = 'o';
            break;
      }
   }

   private boolean isSolved()
   {
      char[][] solvedBoard = { { ' ', ' ', 'o', 'o', 'o', ' ', ' ' },
            { ' ', ' ', 'o', 'o', 'o', ' ', ' ' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', '•', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { ' ', ' ', 'o', 'o', 'o', ' ', ' ' },
            { ' ', ' ', 'o', 'o', 'o', ' ', ' ' }, };
      for (int i = 0; i < board.length; i++)
         for (int j = 0; j < board[0].length; j++)
            if (board[i][j] != solvedBoard[i][j])
               return false;
      return true;
   }
}
