public class Main
{
   public static void main(String[] args)
   {
      PegSolver solver = new PegSolver();
      long timeStart = System.currentTimeMillis();
      solver.solve();
      long timeStop = System.currentTimeMillis();

      double time = (timeStop - timeStart) / (double) 1000;
      System.out.println("Time spent: "
            + (time >= 1 ? time + " s" : time * 1000 + " ms"));
   }
}
