# ADS-Group-Project
### CSU22012: Data Structures and Algorithms II Group Project

Group Members:
- Aaron Readman
- Ralph Swords
- Tom Roberts
- .

----------------------

Given the following input files (obtained via [TransLink Open API](https://developer.translink.ca/))
- **stops.txt** – list of all bus stops in the system, ~8,000 entries 
`stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station`
- **transfers.txt** – list of possible transfers and transfer times between stops, ~5,000 entries 
`from_stop_id,to_stop_id,transfer_type,min_transfer_time`
- **stop_times.txt** - daily schedule containing the trip times of all routes on all stops, ~1,7 million entries
`trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled`

----------------------

### Functional Requirements
1. **Shortest Path between 2 Stops**
    - Finding shortest paths between 2 bus stops (as input by the user), returning the list of stops en route as well as the associated “cost”. 
2. **Search for a Bus Stop**
    - Searching for a bus stop by full name or by the first few characters in the name, using a ternary search tree (TST), returning the full stop information for each stop matching the search criteria (which can be zero, one or more stops).
3. **Search for All Trips**
    - Searching for all trips with a given arrival time, returning full details of all trips matching the criteria (zero, one or more), sorted by trip id.
4. **Front Interface**
    - Provide front interface enabling selection between the above features or an option to exitthe programme, and enabling required user input. It does not matter if this is command-line or graphical, as long as functionality/error checking is provided. 

----------------------

