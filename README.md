# How to run this?

To run this tests you have to get an API key from this website:
https://apilayer.com/marketplace/exchangerates_data-api#pricing

Then set this api as an environmental variable in your IDE. For example in IntellJ choose [Edit Configuration] in the dropdown next to [Run]. Then choose CucumberRunner and add in [Environmental Variables] an API key in this form: 
apikey = xxxx123456789xxxxx

# Places for improvements: 
1) Add logging 
2) Mock data to check if API returns valid rates
