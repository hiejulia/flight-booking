public class SalesTicketingService {
    private static Map<Integer, Pricing> pricingMap = new HashMap<>();
	private static final double EXTRA_HOP_DISCOUNT = 0.9;
    private static final double LAYER_DISCOUNT = 0.9;
    
    /**
     * 
     * Load pricing 
     */

    static void loadPricing() throws IOException
	{
		if( pricingMap.isEmpty() )
		{
			InputStream inputStream = SalesTicketingService.class.getResourceAsStream( "/resources/sales.csv" );
			CSVReader reader = new CSVReader( new InputStreamReader( inputStream ), '\t' );
            String[] nextLine;
            
			while( (nextLine = reader.readNext()) != null )
			{
				Pricing pricing = new Pricing();
				pricing.flightNumber = Integer.parseInt( nextLine[0] );
				pricing.basePrice = Integer.parseInt( nextLine[1] );
				pricing.availability = new int[nextLine.length - 2];
				for( int index = 2; index < nextLine.length; index++ )
				{
					pricing.availability[index - 2] = Integer.parseInt( nextLine[index] );
                }
                // put 
				pricingMap.put( pricing.flightNumber, pricing );
			}
		}
	}

}