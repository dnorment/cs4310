from exceptions import TooManyJobsException
from jobfile import generateJobs, readJobs
from job import Job

from scheduler import FirstComeFirstServe, ShortestJobFirst, RoundRobin2, RoundRobin5

from scheduletable import table

NUM_JOBS = 5
if NUM_JOBS > 30:
    raise TooManyJobsException("Too many jobs") #project desc limits to 30 jobs
generateJobs(NUM_JOBS)

print("RR5")
rr5 = RoundRobin5(readJobs(NUM_JOBS))
rr5.run()
print(rr5.avgTurnaround())
print(table(rr5.scheduleTable))
