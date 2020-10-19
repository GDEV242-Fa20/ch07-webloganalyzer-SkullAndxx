    /**
     * Read web server data and analyse hourly access patterns.
     * 
     * 10-19-2020 Added filename intake to constructor. Enter appropriate filename
     * to test by creating the file using the LogfileCreator.
     * 
     * @author Erick Rubio
     * @version    2020.10.19
     */
    public class LogAnalyzer
    {
        // Where to calculate the hourly access counts.
        private int[] hourCounts;
        // Where to calculate the days of the week access counts.
        private int[] dayCounts;
        // Where to calculate the monthly access counts.
        private int[] monthCounts;
        // Where to calculate the yearly access counts.
        private int[] yearlyCounts;
        // Use a LogfileReader to access the data.
        private LogfileReader reader;
    
        /**
         * Create an object to analyze hourly web accesses.
         */
        public LogAnalyzer()
        { 
            // Create the array object to hold the hourly
            // access counts.
            hourCounts = new int[24];
            dayCounts = new int[7];
            monthCounts = new int[12];
            yearlyCounts = new int[6];
            // Create the reader to obtain the data.
            reader = new LogfileReader("demo.log");
        }
        /**
         * Create an object to analyze hourly web accesses.
         * Exercise 7.12
         * @param enter the name of the file to query
         */
        public LogAnalyzer(String filename)
        { 
            // Create the array object to hold the hourly
            // access counts.
            hourCounts = new int[24];
            dayCounts = new int[7];
            monthCounts = new int[12];
            yearlyCounts = new int[6];
            // Create the reader to obtain the data.
            reader = new LogfileReader(filename);
            //reader = new LogfileReader("demo.log");
        }
        
        /**
         * Analyze the hourly access data from the log file.
         */
        public void analyzeHourlyData()
        {
            while(reader.hasNext()) {
                LogEntry entry = reader.next();
                
                int hour = entry.getHour();
                int month = entry.getMonth()-1;
                int day = entry.getDay()-1;
                //day = day % 7;
                int year = entry.getYear();
                
                hourCounts[hour]++;
                dayCounts[day]++;
                monthCounts[month]++;
                //yearlyCounts[year]++;
            }
        }
    
        /**
         * Print the hourly counts.
         * These should have been set with a prior
         * call to analyzeHourlyData.
         */
        public void printHourlyCounts()
        {
            System.out.println("Hr: Count");
            for(int hour = 0; hour < hourCounts.length; hour++) {
                System.out.println(hour + ": " + hourCounts[hour]);
            }
        }
    
        /**
         * Print the monthly counts.
         * These should have been set with a prior
         * call to analyzeHourlyData.
         */
        public void printMonthlyCounts()
        {
            System.out.println("Month : Count");
            for(int month = 0; month < monthCounts.length; month++) {
                System.out.println(month + ": " + monthCounts[month]);
            }
        } 
        /**
         * Print the day counts.
         * These should have been set with a prior
         * call to analyzeHourlyData.
         */
        public void printDayCounts()
        {
            System.out.println("Day of the Week : Count");
            for(int day = 0; day < dayCounts.length; day++) {
                System.out.println(day + ": " + dayCounts[day]);
            }
        }    
        
        /**
         * Print the lines of data read by the LogfileReader
         */
        public void printData()
        {
            reader.printData();
        }
        /**
         * Count the total number of records in the file
         * Exercise 7.14
         *  @return total number of records
         */
        public int numberOfAccesses(){
        
            int total = 0;
            
            while(reader.hasNext()) {
                LogEntry entry = reader.next();
                total++;
            }
            return total;
        }
        /**
         * Find the busiest hour in 24 hours
         * Exercise 7.15
         * @return Highest number hour
         */
        public void busiestHour(){
        
           int busiestHour = 0;
           int hour = 0;
           
           //for each will not give index
           // for(int hour : hourCounts){
               // if(hour > busiestHour){
                   // busiestHour = hour;
               // } 
           // }
           for(int index = 0; index < hourCounts.length; index++){
               if(hourCounts[index] > busiestHour){
                   busiestHour = hourCounts[index];
                   hour = index;
                   //System.out.println(hour + " : " + busiestHour);
               }
           }
           System.out.println(hour + " : " + busiestHour);
        }
        /**
         * Find the least busy hour in 24 hours
         * Exercise 7.16
         * @return qiuetest hour
         */
        public void qiuetestHour(){
           int qiuetestHour = 0;
           
           //This allows me to get the busiest time to use as my max value
           for(int h : hourCounts){
               if(h > qiuetestHour){
                  qiuetestHour = h;
               } 
           }
           
           int hour = 0;
           
           
           for(int index = 0; index < hourCounts.length; index++){
               //make sure 0 is not used.
               if(hourCounts[index] != 0){
                   //thanks to the max value of qiuetestHour I can now 
                   //compare accordingly because it is not 0
                   if(hourCounts[index] < qiuetestHour){
                       qiuetestHour = hourCounts[index];
                       hour = index;
                       //System.out.println(hour + " : " + qiuetestHour);
                   }           
               }
    
           }
     
           System.out.println(hour + " : " + qiuetestHour);
        }      
        /**
         * Find a 2 hour block that is the busiest
         * Exercise 7.18
         * @return The highest number in a 2 hour block
         */
        public void busiest2Hours(){
        
           int busiestHour = 0;
           int hour = 0;
    
           //minus 1 helps keep everything inbounds during run
           for(int index = 0; index < hourCounts.length-1; index++){
               if(hourCounts[index+1] < hourCounts.length){
                   //lock in sum of indexes
                   int num1 = hourCounts[index] + hourCounts[index+1];
                   //compare sum to previously saved sum
                   if(num1 > busiestHour){
                       //set new sum and save current index
                       busiestHour = num1;
                       hour = index;
                       //System.out.println(hour + " : " + busiestHour);
                   } 
                   
               }
           }
           System.out.println(hour + " : " + busiestHour);
        }      
    
        /** 
         * Identify the busiest month of the year
         */
        public void mostAccessPerMonth(){
        int busiestCount = 0;
        int month = 0;
       
       for(int index = 0; index < monthCounts.length; index++){
          if(monthCounts[index] > busiestCount){
             busiestCount = monthCounts[index];
             month = index;
         }
       }
        System.out.println(month + " : " + busiestCount);        
       }
        /** 
         * Identify the average access in a month
         */
        public void averageAccessPerMonth(){
        int totalAccess = 0;
       
       for(int index = 0; index < monthCounts.length; index++){
           totalAccess += monthCounts[index];
       }
        int averageMonthAccess = totalAccess/monthCounts.length;
        System.out.println("Average: " + averageMonthAccess);        
       }
       
       /** 
       * Identify the busiest day of the week Sunday-Saturday
       */
       public void busiestDayOfWeek(){
           int busiestDay = 0;
           int dayOfWeek = 0;
           
            for(int index = 0; index < dayCounts.length; index++){
               if(dayCounts[index] > busiestDay){
                  busiestDay = dayCounts[index];
                  dayOfWeek = index;
              }
           }
           System.out.println(dayOfWeek + " : " + busiestDay);        
       }
}
