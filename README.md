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
  - ###### stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station
- **transfers.txt** – list of possible transfers and transfer times between stops, ~5,000 entries 
  - ###### from_stop_id,to_stop_id,transfer_type,min_transfer_time
- **stop_times.txt** - daily schedule containing the trip times of all routes on all stops, ~1,7 million entries
  - ###### trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled
