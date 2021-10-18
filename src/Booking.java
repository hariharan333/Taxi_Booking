import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Booking {

    public static void bookTaxi(int cutomerId,char pickupPoint,char dropPoint,int pickupTime,List<Taxi>freeTaxi)
    {
        //to find nearest
        int min = 999;
        int disbtpickupanddrop = 0;
        //this trip earning
        int earnings = 0;
        //when the taxi will be free next
        int nextfreetime = 0;
        //where taxi is after trip is over
        char nextSpot = 'Z';

        //booked taxi
        Taxi bookedTaxi = null;
        //all details of current trip as string
        String tripDetail = "";
        for(Taxi t : freeTaxi)
        {
            int distanceBtCustomerAndTaxi = Math.abs((t.currentSpot - '0')-(pickupPoint - '0')) *15;
            if(distanceBtCustomerAndTaxi < min) {
                min = distanceBtCustomerAndTaxi;
                bookedTaxi = t;
                //min = distanceBtCustomerAndTaxi;
                // distance between pickup and drop
                disbtpickupanddrop = Math.abs((pickupPoint - '0') - (dropPoint - '0')) * 15;

                //earning
                earnings = (disbtpickupanddrop - 5) * 10 + 100;

                //drop time calculated
                int dropTime = pickupTime + disbtpickupanddrop / 15;

                //when taxi will be free
                nextfreetime = dropTime;

                //taxi will be a drop point after the trip
                nextSpot = dropPoint;

                //creating trip details like given format
                tripDetail = cutomerId + "              " + cutomerId + "               " + pickupPoint + "       " +
                        dropPoint + "           " + pickupTime + "                " + dropTime + "            " + earnings;
            }
        }
        bookedTaxi.setDetails(true,nextSpot,nextfreetime,bookedTaxi.totalEarns+earnings,tripDetail);
        //Booked successfully
        System.out.println("taxi " +bookedTaxi.id + " booked");
    }
    public static List<Taxi> createTaxi(int n)
    {
        List<Taxi>taxis = new ArrayList<Taxi>();
        for(int i=0;i<n;i++)
        {
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }
    public static List<Taxi> getFreeTaxi(List<Taxi>taxis,char pickupPoint,int pickupTime)
    {
        List<Taxi> freeTaxis = new ArrayList<Taxi>();
        for(Taxi t:taxis)
        {
            if(t.freeTime < pickupTime && Math.abs((t.freeTime - '0')-(pickupPoint - '0')) <= (pickupPoint - t.freeTime))
                freeTaxis.add(t);
        }
        return freeTaxis;
    }
    public static void main(String[] args)
    {
        //create 4 taxi
        List<Taxi> taxies = createTaxi(4);

        Scanner s = new Scanner(System.in);
        int id = 1;
        while(true)
        {
            System.out.println("0 - > Book Taxi");
            System.out.println("1 - > Print Taxi Details");
            int choice = s.nextInt();
            switch (choice)
            {
                case 0:
                {
                    int CustomerId = id;
                    System.out.println("Enter pickup point");
                    char pickupPoint = s.next().charAt(0);
                    System.out.println("Enter drop point");
                    char dropPoint = s.next().charAt(0);
                    System.out.println("Enter the pickup time");
                    int pickupTime = s.nextInt();

                    //check if pickup and drop point is valid of not
                    if(pickupPoint < 'A' || dropPoint >'F' || pickupPoint >'F' || dropPoint <'A')
                    {
                        System.out.println("Valid pickup and drop point are A , B , C , D , E , F existing");
                        return ;
                    }
                    //get all free taxis that can reach customer on or before pick up time
                    List<Taxi>freeTaxi = getFreeTaxi(taxies,pickupPoint,pickupTime);
                    if(freeTaxi.size() == 0)
                    {
                        System.out.println("No Taxi can be allocated exiting ....");
                        return ;
                    }
                    //Sort the taxis based total earnings
                    Collections.sort(freeTaxi,(a,b)->a.totalEarns - b.totalEarns);
                    // 3 1 2 ---> 1 2 3

                    //get free taxi nearest to us
                    bookTaxi(id,pickupPoint,dropPoint,pickupTime,freeTaxi);
                    id++;
                    break;
                }
                case 1:
                {
                    //two function print details
                    for(Taxi t:taxies)
                        t.printTaxiDetails();
                    for(Taxi t:taxies)
                        t.printDetails();
                    break;
                }
                default:
                    return;
            }
        }
    }
}
