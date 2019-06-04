/**
* Name: Grace Bui
* NetID: bhg27
*
* Execution: java NBody
*
* use Newton's universal law of gravitation and Java 
* to simulate the manner in which celestial bodies interact
* 
*/
public class NBody {    
   public static void main(String[] args) {
      //  declare the elapsed time of simulation
      double simulationTime = Double.parseDouble(args[0]); 
      double timeStep =  Double.parseDouble(args[1]);
      
      // Open the file for reading
      String filename = args[2]; 
      In inStream = new In(filename);
      
   // Read the numParticles and radius variables
      
      int numParticles = inStream.readInt();
      double radius = inStream.readDouble();
      
      //Declare double arrays
      double[] mass = new double[numParticles];
      double[] px   = new double[numParticles];
      double[] py   = new double[numParticles];
      double[] vx   = new double[numParticles];
      double[] vy   = new double[numParticles];
      String[] img  = new String[numParticles];        
        
         
      // Use loop to read the values in the numParticles
      for (int i = 0; i < numParticles; i++) {
         mass[i]   = inStream.readDouble();
         px[i]     = inStream.readDouble();
         py[i]     = inStream.readDouble();
         vx[i]     = inStream.readDouble();
         vy[i]     = inStream.readDouble();
         img[i]    = inStream.readString();
         
         //Draw particles
         PennDraw.picture(px[i], py[i], img[i]); 
         
      }
       
      StdAudio.play("2001.mid");
           
      //Draw the time loop
      
      PennDraw.enableAnimation(30);
      
      for (double j = 0; j < simulationTime; j = j + timeStep) {
         PennDraw.clear();
      
         PennDraw.setXscale(-radius, radius);
         PennDraw.setYscale(-radius, radius);
         PennDraw.picture(0.5, 0.5, "starfield.jpg");
         
         double [] fx = new double [numParticles];
         double [] fy = new double [numParticles];
         
         for (int a = 0; a < numParticles; a++) {
         
            double G = 6.67e-11;
            
            fx[a] = 0.0;
            fy[a] = 0.0;
            
            for (int b = 0; b < numParticles; b++) {
               if (b != a) {
               
                  double deltaX = px[b] - px[a];
                  double deltaY = py[b] - py[a];
                  double d = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                  double F = ((G * mass[a]) / (d * d)) * mass[b]; 
                  fx[a] = F * deltaX / d;
                  fy[a] = F * deltaY / d;
               
                  double ax = fx[a] / mass[a];
                  double ay = fy[a] / mass[a];
               
               //updating velocity of particles
               
                  vx[a] = vx[a] + timeStep * ax;
                  vy[a] = vy[a] + timeStep * ay;
               }
            }
         
         }
        
         for (int i = 0; i < numParticles; i++) {
         
         //Redraw the particles   
            px[i] = px[i] + timeStep * vx[i];
            py[i] = py[i] + timeStep * vy[i];
            PennDraw.picture(px[i], py[i], img[i]);
         }
         
             
         PennDraw.advance();    
              
      }
      System.out.printf("%d\n", numParticles);
      System.out.printf("%.2e\n", radius);
      for (int i = 0; i < numParticles; i++) {
         System.out.printf("%12.5e %12.5e %12.5e %12.5e %12.5e %12s\n", 
            mass[i], px[i], py[i], vx[i], vy[i], img[i]);
      }
      
   }
}