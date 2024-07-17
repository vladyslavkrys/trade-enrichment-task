package com.verygoodbank.tes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementation
 * The candidate should implement a Java service, which will:
 * <p>
 * <ul>
 *     <li>Expose an API to enrich trade data (example: trade.csv) with product names from the static data file (product.csv)</li>
 *     <li>Translate the product_id into product_name</li>
 *     <li>Perform data validation:</li>
 *     <li>Ensure that the date is a valid date in yyyyMMdd format, otherwise discard the row and log an error.</li>
 *     <li>If the product name is not available, the service should log the missing mapping and set the product Name as: Missing Product Name.</li>
 *     <li>Be able to handle:</li>
 *     <li>very large sets of trades (millions).</li>
 *     <li>a large set of products (10k to 100k).</li>
 *     <li>Return the enriched trade data.</li>
 * </ul>
 */
@SpringBootApplication
public class TradeEnrichmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeEnrichmentServiceApplication.class, args);
	}

}
