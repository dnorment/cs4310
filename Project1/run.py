from tabulate import tabulate

from jobfile import generateJobs, readJobs

from exceptions import TooManyJobsException
from job import Job
from scheduler import FirstComeFirstServe, ShortestJobFirst, RoundRobin2, RoundRobin5

def table(data):
    return tabulate(data, headers=['Job Number', 'Start time', 'End time', 'Finished job'], tablefmt='github')

NUM_JOBS = 5
if NUM_JOBS > 30:
    raise TooManyJobsException("Too many jobs") #project desc limits to 30 jobs
generateJobs(NUM_JOBS)

print("FCFS")
r = FirstComeFirstServe(readJobs(NUM_JOBS))
r.run()
print(table(r.scheduleTable))

print("SJF")
r = ShortestJobFirst(readJobs(NUM_JOBS))
r.run()
print(table(r.scheduleTable))

print("RR2")
r = RoundRobin2(readJobs(NUM_JOBS))
r.run()
print(table(r.scheduleTable))

print("RR5")
r = RoundRobin5(readJobs(NUM_JOBS))
r.run()
print(table(r.scheduleTable))