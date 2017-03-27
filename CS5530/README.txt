CS 5530 - Phase 2
Jared Shaw & Ryan Park

The user interface I went for was one that would help the user
as much as possible. For example whenever a user id or housing id is
required, a list will usually appear so the user won't have to 
try rememeber all the ids. I enforced a lot of the required 
constraints in the application itself and then also using keys
in the database. 

Requirements:
All the requirements should be working as requested with the exception of
TH browse. This will only filter based on the last given key,
for example if you elect to search both by price range and by city, it will
only do the search on the last query: which in that case is city. basically
it is only set up to run one type of search, so you can either search by
price range, city, keyword, or category, but not any two at the same time.
it does however sort by price, score, etc.